package com.lcworld.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.utils.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.entity.BxwxAppointmentEntity;
import com.lcworld.entity.HysfwAppointmentEntity;
import com.lcworld.entity.TBxwxCommentEntity;
import com.lcworld.entity.TBxwxNoticeEntity;
import com.lcworld.entity.TBxwxOrderEntity;
import com.lcworld.service.BxwxAppointmentService;
import com.lcworld.service.ServiceService;
import com.lcworld.service.TBxwxCommentService;
import com.lcworld.service.TBxwxItemService;
import com.lcworld.service.TBxwxNoticeService;
import com.lcworld.service.TBxwxOrderProcessesService;
import com.lcworld.service.TBxwxOrderService;
import com.lcworld.vo.BxwxAppointmentResultVo;

@RestController
@RequestMapping("appuser/bxwx")
public class TBxwxController {
    private Logger log = LoggerFactory.getLogger(TBxwxController.class);
    @Autowired
    private TBxwxItemService tBxwxItemService;
    @Autowired
    private TBxwxOrderService tBxwxOrderService;
    @Autowired
    private TBxwxOrderProcessesService tBxwxOrderProcessesService;
    @Autowired
    private TBxwxNoticeService tBxwxNoticeService;
    @Autowired
    private TBxwxCommentService tBxwxCommentService;
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private BxwxAppointmentService appointmentService;
   
    /**跳转物业维修
     * @param biz
     * @return
     */
    @RequestMapping("towywx")
    @ResponseBody
    public R towywx(HttpServletRequest request){
       R result = new R();
       String biz = request.getParameter("biz");
       log.debug(biz);
       JSONObject params = FastJSONUtils.getJSONObject(biz);
       Query query = new Query(params);
       result.put("itemlist", tBxwxItemService.queryList(query)); 
       return result;
    }
    
    
    /**发布物业维修
     * @param biz
     * @return
     * @throws Exception 
     */
    @RequestMapping("addOrder")
    @ResponseBody
    public R addOrder(@RequestParam("files") MultipartFile[] files,HttpServletRequest request) throws Exception{
        String biz = request.getParameter("biz");
        JSONObject params = JSONObject.parseObject(biz);
        log.debug(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+">>>>>>>>>>发布物业维修>>>>>>>>>");
        Integer uid = WebUtils.getUid(request);
        TBxwxOrderEntity order = FastJSONUtils.getObject(biz, TBxwxOrderEntity.class);
        order.setUid(uid);
        order.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_BXFW));
        
        /*StringBuilder sb = new StringBuilder();
        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();// 文件原名称
            String filePath = DateUtils.format(new Date())+"/"+System.currentTimeMillis()+fileName.substring(fileName.lastIndexOf("."), fileName.length());
            OSSUtils.uploadFile(filePath, file.getInputStream());
            String imgUrl = OSSConstantKey.OSS_BASE_URL+filePath;
            sb.append(",").append(imgUrl);
            
        }
        if(!StringUtils.isBlank(sb.toString())){
        	order.setMendimgs(sb.toString().substring(1));
        }*/
        
        
        JSONObject obj = WebUtils.uploadFiles(files, request, RedisConst.USER_BXWX_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
        if (0 == obj.getIntValue("errorCode")) {
            order.setMendimgs(obj.getString("data"));
        }
        
      //预约信息
        //{date:"2017-10-18",intervalids:"1,2,3"}
        JSONArray appointmentInfo = params.getJSONArray("appointmentInfo");
        List<BxwxAppointmentEntity> list = new ArrayList<>();
        for (int i = 0; i < appointmentInfo.size(); i++) {
        	BxwxAppointmentEntity app = appointmentInfo.getObject(i, BxwxAppointmentEntity.class);
        	app.setUid(params.getInteger("uid"));
        	list.add(app);
		}
        R r = tBxwxOrderService.generateOrder(order,list);
        log.debug("result:"+r);
        //tBxwxOrderService.saveorder(order);
        return R.ok();
    }
    
    
    /**获取订单列表
     * @param request
     * @return
     */
    @RequestMapping("orderlist")
    @ResponseBody
    public R orderlist(HttpServletRequest request){
        R result = new R();
        String biz = request.getParameter("biz");
        log.debug(biz);
        Integer uid = WebUtils.getUid(request);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        params.put("statuslist", addTypeParams(params));
        if (ValidateUtil.isValid(uid)) {
			params.put("uid", uid);
		}else{
			//return R.error(1,"未登录");
		}
        params.put("sidx", "createtime");
        params.put("order", "desc");
        Query q = new Query(params);
        result.put("orderlist", tBxwxOrderService.queryOrderlist(q)) ;
        result.put("serviceInfo", serviceService.queryService(APPConstant.TYPE_BXFW));
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


    /**获取订单详情
     * @param biz
     * @return
     */
    @RequestMapping("orderdetail")
    @ResponseBody
    public R orderdetail(String biz){
        R result = new R();
        log.debug(biz);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        TBxwxOrderEntity order = new  TBxwxOrderEntity();
        order.setOrderchange(0);
        order.setId(params.getInteger("orderid"));
        tBxwxOrderService.update(order);
        result.put("detail", tBxwxOrderService.queryOrderDetail(params)) ;
        return result;
    }
    
    /**获取订单状态列表
     * @param biz
     * @return
     */
    @RequestMapping("orderstatuslist")
    @ResponseBody
    public R orderstatuslist(String biz){
        log.debug(biz);
        R result = new R();
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        params.put("sidx", "createtime");
        params.put("order", "desc");
        result.put("detail", tBxwxOrderProcessesService.queryProcessList(params)) ;
        return result;
    }
    
    
    /**获取消息
     * @param biz
     * @return
     */
    @RequestMapping("noticelist")
    @ResponseBody
    public R noticelist(String biz){
        log.debug(biz);
        R result = new R();
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        params.put("sidx", "createtime");
        params.put("order", "asc");
        Query q = new Query(params);
        result.put("noticelist", tBxwxNoticeService.queryList(q));
        return result;
    }
    
    /**添加消息
     * @param biz
     * @return
     */
    @RequestMapping("addnotice")
    @ResponseBody
    public R addnotice(String biz){
        log.debug(biz);
        TBxwxNoticeEntity notice = FastJSONUtils.getObject(biz, TBxwxNoticeEntity.class);
        TBxwxOrderEntity order = tBxwxOrderService.queryObject(notice.getOrderid());
        if(order.getOrderstatus() ==3 || order.getOrderstatus() == 4 || order.getOrderstatus() == 5 ){
            return R.error(1100, "该订单已结束或已取消");
        }
        notice.setCreatetime(new Date());
        notice.setGetreadstatus(0);
        notice.setGettype(2);
        notice.setUtype(1);
        notice.setGetid(order.getMenderid());
        notice.setPostid(order.getUid());
        tBxwxNoticeService.save(notice);
        return R.ok();
    }
    
    /**取消订单
     * @param biz
     * @return
     */
    @RequestMapping("cancelorder")
    @ResponseBody
    public R cancelorder(String biz){
        log.debug(biz);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        TBxwxOrderEntity order = tBxwxOrderService.queryObject(params.getInteger("orderid"));
        if(order.getOrderstatus() ==1 || order.getOrderstatus() == 2  ){
            order.setOrderchange(1);
            order.setCancelreason(params.getString("cancelreason"));
            order.setOrderstatus(5);
            tBxwxOrderService.update(order);
            return R.ok();
        }else{
            return R.error(1101, "该订单已结束，取消失败");
        }
    }
    
    /**删除订单
     * @param biz
     * @return
     */
    @RequestMapping("delorder")
    @ResponseBody
    public R delorder(String biz){
        log.debug(biz);
        JSONObject params = FastJSONUtils.getJSONObject(biz);
        TBxwxOrderEntity order = tBxwxOrderService.queryObject(params.getInteger("orderid"));
        order.setIsdel(1);
        tBxwxOrderService.update(order);
        return R.ok();
    }
    
    /**发布物业维修
     * @param biz
     * @return
     */
    @RequestMapping("addcomment")
    @ResponseBody//@RequestParam MultipartFile[] files,
    public R addcomment(HttpServletRequest request){
        String biz = request.getParameter("biz");
        log.debug(biz);
        TBxwxCommentEntity comment = FastJSONUtils.getObject(biz, TBxwxCommentEntity.class);
        TBxwxOrderEntity order = tBxwxOrderService.queryObject(comment.getOrderid());
        if(order.getOrderstatus() ==4 ){
            JSONObject obj = WebUtils.uploadFiles(null, request, RedisConst.USER_BXWX_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
            if (0 == obj.getIntValue("errorCode")) {
                comment.setImgs(obj.getString("data"));
            }
            comment.setCreatetime(new Date());
            comment.setMenderid(order.getMenderid());
            comment.setUid(order.getUid());
            tBxwxCommentService.savecomment(comment,order);
            return R.ok();
        }else if(order.getOrderstatus() == 3){
            return R.error(1103,"该订单已进行过评论,不可重复评论");
        }else{
            return R.error(1102, "该订单还没有完成，不能进行评价");
        }
    }
    
    /**
	 * 查询可预约时间
	 * @param req
	 * @return
	 */
	@RequestMapping("/findAvailableTime")
	public R findAvailableTime(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		String date = params.getString("date");
		if (DateUtil.parse(date, "yyyy-MM-dd")==null) {
			return R.error(1,"日期格式有误：使用此格式yyyy-MM-dd");
		}
		Set<BxwxAppointmentResultVo> appointmentList = appointmentService.findAvailableTime(params);
		return R.ok().put("appointmentList", appointmentList);
	}
}
