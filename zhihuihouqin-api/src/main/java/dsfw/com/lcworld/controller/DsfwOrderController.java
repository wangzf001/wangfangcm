package com.lcworld.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.*;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.*;
import com.lcworld.utils.DateUtil;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.validator.Assert;
import com.lcworld.vo.AppointmentResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;


/**
 * 订水服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:20
 */
@RestController
@RequestMapping("appuser/dsfworder")
public class DsfwOrderController {
	private Logger log = LoggerFactory.getLogger(DsfwOrderController.class);
	@Autowired
	private DsfwOrderService dsfwOrderService;
	@Autowired
	private DsfwSendtimeService dsfwSendtimeService;
	@Autowired
	private DsfwOrdercancelReasonService dsfwOrdercancelReasonService;
	@Autowired
	private DsfwCommentService dsfwCommentService;
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private UserWalletService userWalletService;
	@Autowired
    private DsfwAppointmentService appointmentService;
	/**
     * 获取送水时间
     * @param req
     * @return
     */
    @RequestMapping("/sendTimeList")
    public R sendTimeList(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        JSONObject params = JSONObject.parseObject(biz);
        //接口参数
        //查询列表数据
        List<DsfwSendtimeEntity> timeList = dsfwSendtimeService.queryList(params);
        List<DsfwSendtimeEntity> amList = new ArrayList<>();
        List<DsfwSendtimeEntity> pmList = new ArrayList<>();
        for (int i = 0; i < timeList.size(); i++) {
        	DsfwSendtimeEntity time = timeList.get(i);
            time.setTimeStr(time.getStarttime()+"-"+time.getEndtime());
            if (time.getPeriodtype().intValue()==1) {
                amList.add(time);
            }else{
                pmList.add(time);
            }
        }
        Comparator<DsfwSendtimeEntity> comparator = new Comparator<DsfwSendtimeEntity>() {
            @Override
            public int compare(DsfwSendtimeEntity o1, DsfwSendtimeEntity o2) {
                // TODO Auto-generated method stub
                Date date1 = DateUtils.parse(o1.getEndtime(), "HH:mm");
                Date date2 = DateUtils.parse(o2.getEndtime(), "HH:mm");
                return (int)(date1.getTime()-date2.getTime());
            }
        };
        
        Collections.sort(amList,comparator);
        Collections.sort(pmList,comparator);
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("amList", amList);
        resultMap.put("pmList", pmList);
        log.debug("result:"+resultMap);
        return R.ok(resultMap);
    }
    /**
     * 生成订水订单
     * @param req
     * @return
     */
    @IgnoreSign
    @PostMapping("/generateOrder")
    public R generateOrder(HttpServletRequest req,String biz){
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        params.put("uid", req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY));
        //接口参数
        /*String realname = params.getString("realname");
        String mobile = params.getString("mobile");
        Integer addressId = params.getInteger("addressId");
        Integer sendfoodtime = params.getInteger("sendTimeID");
        JSONArray goodsList = params.getJSONArray("goodsList");
        String remark = params.getString("remark");
        String sendtime = params.getString("sendtime");*/
        //goodsList=[{goodsid:1,count:1}]
        //预约信息
        //{date:"2017-10-18",intervalids:"1,2,3"}
        JSONArray appointmentInfo = params.getJSONArray("appointmentInfo");
        //Assert.isBlank(appointmentInfo.toJSONString(),"appointmentInfo is blank");
        List<DsfwAppointmentEntity> list = new ArrayList<>();
        if (appointmentInfo != null){
        	
            for (int i = 0; i < appointmentInfo.size(); i++) {
            	
            	JSONObject item = JSONObject.parseObject(appointmentInfo.get(i).toString());
            	if("尽快送达".equals(item.get("date").toString())){
            		item.put("date", DateUtils.format(new Date()));
            		appointmentInfo.set(i, item);
            		// 不在处理时间冲突问题
            		params.put("check_flag", 0);
            	}
            	
                DsfwAppointmentEntity app = appointmentInfo.getObject(i, DsfwAppointmentEntity.class);
                app.setUid(params.getInteger("uid"));
                list.add(app);
            }
        }
        R r = dsfwOrderService.generateOrder(params,list);
        log.debug("result:"+r);
        return r;
    }
    
    /**
     * 查询订水订单
     * @param req
     * @return
     */
    @RequestMapping("/findOrderList")
    @IgnoreSign
    public R findOrderList(HttpServletRequest req,String biz){
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        params.put("uid", req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY));
        
        //接口参数
        params.put("ordertype", APPConstant.TYPE_DSFW);
        params.put("sidx", "createtime");
        params.put("order", "desc");
        Query query = new Query(params);
        List<DsfwOrderEntity> orderList = dsfwOrderService.queryList(query);
        List<Map<String,Object>> list = converseToMaplist(orderList,false);
        return R.ok().put("list", list);
    }
    private List<Map<String, Object>> converseToMaplist(List<DsfwOrderEntity> orderList,boolean isDetail) {
    	ServiceEntity queryService = serviceService.queryService(APPConstant.TYPE_DSFW);
        String mobile = queryService.getMobile();
    	List<Map<String, Object>> list = new ArrayList<>();
    	for (DsfwOrderEntity order : orderList) {
    		Map<String,Object> resultMap = new HashMap<>();
    		resultMap.put("id", order.getId());
    		resultMap.put("ordercode", order.getOrdercode());
    		resultMap.put("img", order.getDetailList().get(0).getGoods().getImg());
    		resultMap.put("name", order.getDetailList().get(0).getGoods().getName());
    		resultMap.put("goodsid", order.getDetailList().get(0).getGoods().getId());
    		resultMap.put("count", order.getDetailList().get(0).getCount());
    		resultMap.put("totalprice", order.getTotalprice());
    		resultMap.put("status", order.getStatus());
    		resultMap.put("payStatus", order.getPayStatus());
    		resultMap.put("createtime", order.getCreatetime());
    		resultMap.put("serviceMobile", mobile); 
    		resultMap.put("ordertype", APPConstant.TYPE_DSFW);
    		if (order.getStatus().intValue()==2) {
				//有配送人员
    			resultMap.put("dusername", order.getDusername());
    			resultMap.put("dmobile", order.getDmobile());
    			resultMap.put("dphoto", order.getDphoto());
			}
    		if (isDetail) {
    			resultMap.put("sendTime", order.getSendTime()!=null?order.getSendTime():"立即配送");
    			resultMap.put("price", order.getDetailList().get(0).getPrice());
    			resultMap.put("receiverAddress", order.getAddress().getAddress());
    			resultMap.put("receiverName", order.getAddress().getRealname());
    			resultMap.put("receiverMobile", order.getAddress().getMobile());
    			resultMap.put("createtime", order.getCreatetime());
    			resultMap.put("paytype", order.getPayType());
			}
    		list.add(resultMap);
		}
		return list;
	}
	/**
     * 获取订单详情
     * @param req
     * @return
     */
    @RequestMapping("/getOrderDetail")
    public R getOrderDetail(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        JSONObject params = JSONObject.parseObject(biz);
        //接口参数
        Integer oid = params.getInteger("oid");
        params.put("ordertype", APPConstant.TYPE_DSFW);
        List<DsfwOrderEntity> orderList = dsfwOrderService.queryList(params);
        if (ValidateUtil.isValid(orderList)) {
        	List<Map<String, Object>> list = converseToMaplist(orderList,true);
        	Map<String, Object> detail = list.get(0);
            log.debug("result:"+detail);
            return R.ok().put("detail", detail);
        }
        return R.error();
    }
    /**
	 * 退货原因列表
	 * @param req
	 * @return
	 */
	@RequestMapping("/findReasonList")
	public R findReasonList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//查询列表数据
		List<DsfwOrdercancelReasonEntity> reasonList = dsfwOrdercancelReasonService.queryList(params);
		log.debug("reasonList:"+reasonList);
		return R.ok().put("reasonList", reasonList);
	}
    /**
     * 取消订单
     * @param req
     * @return
     */
    @RequestMapping("/orderCancel")
    public R orderCancel(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        JSONObject params = JSONObject.parseObject(biz);
        //接口参数
        Integer oid = params.getInteger("oid");
        JSONArray reasonIds = params.getJSONArray("reasonIds");
        String reasonContent = params.getString("reasonContent");
        R r = dsfwOrderService.orderCancel(params);
        return r;
    }
    /**
     * 添加评价
     * @param req
     * @return
     * @throws IOException 
     */
    @RequestMapping("/addComment")
    public R addComment(HttpServletRequest req,@RequestParam("files") MultipartFile[] imgFile) throws IOException{
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        JSONObject params = JSONObject.parseObject(biz);
        //接口参数
        Integer oid = params.getInteger("oid");
        Double stars = params.getDouble("stars");
        String content = params.getString("content");
        Integer isAnonymous = params.getInteger("isAnonymous");
        DsfwOrderEntity order = dsfwOrderService.queryObject(oid);
        if (order.getStatus()!=APPConstant.TYPE_ORDER_STATUS_FINISHED&&order.getPayStatus()!=APPConstant.TYPE_ORDER_PAY_STATUS_PAYED) {
            return R.error(1002,"非法评论");
        }
        DsfwCommentEntity commentEntity = new DsfwCommentEntity();
        commentEntity.setAnonymous(isAnonymous);
        commentEntity.setContent(content);
        commentEntity.setCreatetime(new Date());
        commentEntity.setOrderid(oid);
        commentEntity.setScore(stars);
        commentEntity.setUid(order.getUid());
        R r = dsfwCommentService.addComment(commentEntity,imgFile);
        return r;
    }
    /**
	 * 删除订单
	 * @param req
	 * @return
	 */
	@RequestMapping("/orderDelete")
	public R orderDelete(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		Integer id = params.getInteger("orderid");
		List<Integer> ids = new ArrayList<>();
		ids.add(id);
		int successNum = dsfwOrderService.invalidOrderBatch(ids.toArray(new Integer[1]));
		int total = ids.size();
		return R.ok().put("successNum", successNum).put("total", total);
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
        Set<AppointmentResultVo> appointmentList = appointmentService.findAvailableTime(params);
        return R.ok().put("appointmentList", appointmentList);
    }
}
