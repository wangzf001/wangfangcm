package com.lcworld.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.FavorConst;
import com.lcworld.consts.RedisConst;
import com.lcworld.dto.TsjyfwOrderDTO;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.TDcfwGetfoodtimeEntity;
import com.lcworld.entity.TsjyNoticeEntity;
import com.lcworld.entity.TsjyfwBookEntity;
import com.lcworld.entity.TsjyfwCommentEntity;
import com.lcworld.entity.TsjyfwOrderEntity;
import com.lcworld.entity.TsjyfwPeriodEntity;
import com.lcworld.entity.TsjyfwUserrecommandEntity;
import com.lcworld.entity.YlfwZjzzNoticeEntity;
import com.lcworld.service.ServiceService;
import com.lcworld.service.TFavorService;
import com.lcworld.service.TsjyNoticeService;
import com.lcworld.service.TsjyUsersearchhistoryService;
import com.lcworld.service.TsjyfwBookService;
import com.lcworld.service.TsjyfwBooktypeService;
import com.lcworld.service.TsjyfwCommentService;
import com.lcworld.service.TsjyfwOrderService;
import com.lcworld.service.TsjyfwPeriodService;
import com.lcworld.service.TsjyfwUserrecommandService;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.FastJSONUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;
import com.lcworld.utils.WebUtils;
import com.lcworld.vo.TstjfwOrderVO;

@RestController
@RequestMapping("appuser/tsjyfw")
public class TsjyController {
    private Logger log = LoggerFactory.getLogger(TsjyController.class);
    @Autowired
    private TsjyfwBookService tsjyfwBookService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private TsjyfwBooktypeService tsjyfwBooktypeService;
    @Autowired
    private TsjyfwCommentService tsjyfwCommentService;
    @Autowired
    private TsjyfwPeriodService tsjyfwPeriodService;
    @Autowired
    private TsjyfwOrderService tsjyfwOrderService;
    @Autowired
    private TsjyfwUserrecommandService tsjyfwUserrecommandService;
    @Autowired
    private TFavorService tFavorService;
    @Autowired
    private TsjyUsersearchhistoryService tsjyUsersearchhistoryService;
    @Autowired
    private TsjyNoticeService tsjyNoticeService;
    /**跳转物业维修
     * @param biz
     * @return
     */
    @RequestMapping("/indexbooklist")
    @ResponseBody
    @IgnoreSign
    @IgnoreToken
    public R index(HttpServletRequest request){
       R result = new R();
       String biz = request.getParameter("biz");
       log.debug(biz);
       JSONObject params = FastJSONUtils.getJSONObject(biz);
       Integer order = params.getInteger("order");
       if(order == null){
           params.put("order", 1);
       }
       Query query = new Query(params);
       result.put("booklist", tsjyfwBookService.queryBookList(query)); 
       return result;
    }
    
    /**跳转物业维修
     * @param biz
     * @return
     */
    @RequestMapping("/tosearch")
    @ResponseBody
    public R tosearch(HttpServletRequest request){
        R result = new R();
        String biz = request.getParameter("biz");
        log.debug(biz);
        Integer uid = WebUtils.getUid(request);
        result.put("typelist", gettypelist());
        if(ValidateUtil.isValid(uid)){
            JSONObject obj = new JSONObject();
            obj.put("uid", uid);
            Query q = new Query(obj);
            result.put("historylist", tsjyUsersearchhistoryService.queryList(q));
        }
        return result;
    }
    
    /**search
     * @param biz
     * @return
     */
    @RequestMapping("/search")
    @ResponseBody
    public R search(HttpServletRequest request){
        R result = new R();
        String biz = request.getParameter("biz");
        log.debug(biz);
        Integer uid = WebUtils.getUid(request);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        params.put("uid", uid);
        if(params.getBooleanValue("issearch")){
            tsjyUsersearchhistoryService.savehistory(params);
        }
        addparams(params.getIntValue("type"), params.getString("keyword"), params);
        Query q = new Query(params);
        result.put("booklist", tsjyfwBookService.queryBookList(q));
        result.put("total", tsjyfwBookService.queryTotal(q));
        return result;
    }
    
    /**search
     * @param biz
     * @return
     */
    @RequestMapping("/cleansearchhistory")
    @ResponseBody
    public R cleansearchhistory(HttpServletRequest request){
        R result = new R();
        String biz = request.getParameter("biz");
        log.debug(biz);
        Integer uid = WebUtils.getUid(request);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        params.put("uid", uid);
        tsjyUsersearchhistoryService.deleteByWhere(params);
        return result;
    }
    
    private List<Map<String, String>> gettypelist(){
        ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
        addmap(list,"0","所有内容");
        addmap(list,"1","正题名");
        addmap(list,"2","其他题名");
        addmap(list,"3","著者");
        addmap(list,"4","主题词");
        addmap(list,"5","出版者");
        addmap(list,"6","索书号");
        addmap(list,"7","isbn");
        addmap(list,"8","条码号");
        return list;
    }
    
    private void addmap(ArrayList<Map<String, String>> list, String id, String value) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("id", id);
        map.put("value", value);
        list.add(map);
    }
    
    private void addparams(int type,String key,JSONObject params){
       
        switch(type){
        case 0: addAll(params,key);break;
        case 1: params.put("title", key);break;
        case 2: params.put("subtitle", key);break;
        case 3: params.put("author", key);break;
        case 4: params.put("keywords", key);break;
        case 5: params.put("publisher", key);break;
        case 6: params.put("searchid", key);break;
        case 7: params.put("isbn", key);break;
        case 8: params.put("barcode", key);break;
        }
        
    }

    private void addAll(JSONObject params, String key) {
       params.put("title", key);
       params.put("subtitle", key);
       params.put("author", key);
       params.put("keywords", key);
       params.put("publisher", key);
       params.put("searchid", key);
       params.put("isbn", key);
       params.put("barcode", key);
    }

    /**跳转物业维修
     * @param biz
     * @return
     */
    @RequestMapping("/getTopPicture")
    @ResponseBody
    @IgnoreSign
    @IgnoreToken
    public R getTopPicture(HttpServletRequest request){
        R result = new R();
        String biz = request.getParameter("biz");
        log.debug(biz);
        ServiceEntity service = serviceService.queryService(APPConstant.TYPE_TSJYFW);
        result.put("photo", service.getTopphoto()==null?"":service.getTopphoto());
        return result;
    }
    
    
    
    
    /**读者推荐
     * @param biz
     * @return
     */
    @RequestMapping("/recommand")
    @ResponseBody
    public R recommand(HttpServletRequest request){
        R result = new R();
        String biz = request.getParameter("biz");
        log.debug(biz);
        Integer uid = WebUtils.getUid(request);
        TsjyfwUserrecommandEntity recommand = FastJSONUtils.getObject(biz, TsjyfwUserrecommandEntity.class);
        recommand.setCreatetime(new Date());
        recommand.setUid(uid);
        recommand.setStatus(1);//未处理
        tsjyfwUserrecommandService.save(recommand);
        return result;
    }
    
    
    /**获取图书详情
     * @param biz
     * @return
     */
    @RequestMapping("/getbookdetail")
    @ResponseBody
    public R getbookdetail(HttpServletRequest request){
        R result = new R();
        String biz = request.getParameter("biz");
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        Integer bookid = params.getInteger("bookid"); 
        Integer uid = WebUtils.getUid(request);
        TsjyfwBookEntity book = tsjyfwBookService.queryObject(bookid);
        result.put("authorbrief", book.getAuthorbrief());
        result.put("content", book.getContent());
        result.put("favorstatus", tFavorService.queryFavorStatus(uid,FavorConst.FAVORTYPE_BOOK,bookid));
        return result;
    }
    
    /**借阅图书
     * @param biz
     * @return
     */
    @RequestMapping("/bookorder")
    @ResponseBody
    public R bookorder(HttpServletRequest request){
        R result = new R();
        String biz = request.getParameter("biz");
        log.debug(biz);
        Integer uid = WebUtils.getUid(request);
        TstjfwOrderVO ordervo = FastJSONUtils.getObject(biz, TstjfwOrderVO.class);
        ordervo.setUid(uid);
        JSONObject res = tsjyfwOrderService.saveorder(ordervo);
        if(0 != res.getInteger("errCode") ){
            result = R.error(res.getIntValue("errCode"),res.getString("msg"));
        }
        return result;
    }
    
    /**获取订单列表
     * @param biz
     * @return
     */
    @RequestMapping("/orderlist")
    @ResponseBody
    public R orderlist(HttpServletRequest request){
        R result = new R();
        String biz = request.getParameter("biz");
        log.debug(biz);
        Integer uid = WebUtils.getUid(request);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        params.put("statuslist", addTypeParams(params));
        params.put("uid", uid);
        Query q = new Query(params);
        result.put("orderlist", tsjyfwOrderService.queryorderlist(q));
        result.put("service", serviceService.queryService(APPConstant.TYPE_TSJYFW));
        return result;
    }
    
    /**获取订单详情
     * @param biz
     * @return
     */
    @RequestMapping("/orderdetail")
    @ResponseBody
    public R orderlist(String biz){
        R result = new R();
        log.debug(biz);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        Query q = new Query(params);
        params.put("statuslist", addTypeParams(params));
        List<TsjyfwOrderDTO> list = tsjyfwOrderService.queryorderlist(q);
        TsjyfwOrderDTO order = null;
        result.put("detail", ValidateUtil.isValid(list)?order = list.get(0):null );
        if(ValidateUtil.isValid(order)){
            TsjyfwOrderEntity o = new TsjyfwOrderEntity();
            o.setId(order.getOrderid());
            o.setChanges(0);
            tsjyfwOrderService.update(o);
        }
        return result;
    }
    
    /**取消订单
     * @param biz
     * @return
     */
    @RequestMapping("/cancelorder")
    @ResponseBody
    public R cancelorder(String biz){
        R result = new R();
        log.debug(biz);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        TsjyfwOrderEntity order = tsjyfwOrderService.queryObject(params.getInteger("orderid"));
        order.setStatus(5);
        order.setCancelreason(params.getString("cancelreason"));
        order.setChanges(1);
        tsjyfwOrderService.update(order);
        return result;
    }
    
    /**删除订单
     * @param biz
     * @return
     */
    @RequestMapping("/delorder")
    @ResponseBody
    public R delorder(String biz){
        R result = new R();
        log.debug(biz);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        TsjyfwOrderEntity order = tsjyfwOrderService.queryObject(params.getInteger("orderid"));
        order.setIsdel(1);
        order.setChanges(0);
        tsjyfwOrderService.update(order);
        return result;
    }
    
    /**添加评论
     * @param biz
     * @return
     */
    @RequestMapping("/addcomment")
    @ResponseBody
    public R addcomment(@RequestParam MultipartFile[] files,HttpServletRequest request){
        String biz = request.getParameter("biz");
        log.debug(biz);
        TsjyfwCommentEntity comment = FastJSONUtils.getObject(biz,TsjyfwCommentEntity.class);
        TsjyfwOrderEntity order = tsjyfwOrderService.queryObject(comment.getOrderid());
        if(order.getStatus().intValue() ==4 ){
            JSONObject obj = WebUtils.uploadFiles(files, request, RedisConst.USER_YLFW_YYGH_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
            if (0 == obj.getIntValue("errorCode")) {
                comment.setImgs(obj.getString("data"));
            }
            comment.setCreatetime(new Date());
            comment.setBookid(order.getBookid());
            comment.setUid(order.getUid());
            tsjyfwCommentService.savecomment(comment,order);
            return R.ok();
        }else if(order.getStatus() == 3){
            return R.error(1103,"该订单已进行过评论,不可重复评论");
        }else{
            return R.error(1102, "该订单还没有完成，不能进行评价");
        }
    }
    
    
    
    
    
    
    /**获取图书分类
     * @param biz
     * @return
     */
    @RequestMapping("/gettypelist")
    @ResponseBody
    @IgnoreSign
    @IgnoreToken
    public R gettypelist(String biz){
        R result = new R();
        result.put("typelist", tsjyfwBooktypeService.queryList(new HashMap<String,Object>()));
        return result;
    }
    
    /**获取图书列表
     * @param biz
     * @return
     */
    @RequestMapping("/getbooklist")
    @ResponseBody
    @IgnoreSign
    @IgnoreToken
    public R getbooklist(String biz){
        R result = new R();
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        Integer order = params.getInteger("order");
        if(order == null){
            params.put("order", 1);
        }
        Query query = new Query(params);
        result.put("booklist", tsjyfwBookService.queryBookList(query)); 
        return result;
    }
    /**获取时间
     * @param biz
     * @return
     */
    @RequestMapping("/gettime")
    @ResponseBody
    @IgnoreSign
    @IgnoreToken
    public R gettime(String biz){
        R result = new R();
        JSONObject map = new JSONObject();
        map.put("sidx", "sort");
        map.put("order", "asc");
        List<TsjyfwPeriodEntity> list = tsjyfwPeriodService.querytimeList(map);
        
        List<TsjyfwPeriodEntity> amList = new ArrayList<>();
        List<TsjyfwPeriodEntity> pmList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TsjyfwPeriodEntity time = list.get(i);
            if ("1".equalsIgnoreCase(time.getTypeid())) {
                amList.add(time);
            }else{
                pmList.add(time);
            }
        }
        Comparator<TsjyfwPeriodEntity> comparator = new Comparator<TsjyfwPeriodEntity>() {
            @Override
            public int compare(TsjyfwPeriodEntity o1, TsjyfwPeriodEntity o2) {
                // TODO Auto-generated method stub
                int i = Integer.parseInt(o1.getTimeperiod().split("-")[0].split(":")[0]);
                int j = Integer.parseInt(o2.getTimeperiod().split("-")[0].split(":")[0]);
                return i-j;
            }
        };
        Collections.sort(amList,comparator);
        Collections.sort(pmList,comparator);
        result.put("amList", amList);
        result.put("pmList", pmList);
        return result;
    }
    
    private List<Integer> addTypeParams(JSONObject params) {
        int type = params.getInteger("type").intValue();
        List<Integer> statuslist = new ArrayList<Integer>();
        switch(type){
        case 3 : statuslist.add(3);statuslist.add(4);break;
        default: statuslist.add(type);break;
        }
        return statuslist;
        
    }
    
    /**
     * 获取消息
     * 
     * @param biz
     * @return
     */
    @RequestMapping("noticelist")
    @ResponseBody
    public R noticelist(HttpServletRequest request) {
        R result = new R();
        String biz = request.getParameter("biz");
        log.debug(biz);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        params.put("uid", WebUtils.getUid(request));
        params.put("sidx", "createtime");
        params.put("order", "asc");
        Query q = new Query(params);
        List<TsjyNoticeEntity> list;
        Collections.reverse(list = tsjyNoticeService.queryNoticeList(q));
        result.put("noticelist",list );
        result.put("total", tsjyNoticeService.queryNoticeListTotal(q));
        return result;
    }

    /**
     * 添加消息
     * 
     * @param biz
     * @return
     */
    @RequestMapping("addnotice")
    @ResponseBody
    public R addnotice(HttpServletRequest request) {
        String biz = request.getParameter("biz");
        log.debug(biz);
        TsjyNoticeEntity notice = FastJSONUtils.getObject(biz, TsjyNoticeEntity.class);
        notice.setCreatetime(new Date());
        notice.setGetreadstatus(0);
        notice.setGettype(2);
        notice.setUtype(1);
        notice.setPostid(WebUtils.getUid(request));
        tsjyNoticeService.save(notice);
        return R.ok();
    }

    
}
