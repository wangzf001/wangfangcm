package com.lcworld.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.lcworld.entity.*;
import com.lcworld.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.annotation.IgnoreSign;
import com.lcworld.annotation.IgnoreToken;
import com.lcworld.consts.APPConstant;
import com.lcworld.consts.RedisConst;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.impl.HysfwAppointmentServiceImpl;
import com.lcworld.utils.DateUtil;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;
import com.lcworld.vo.HysfwAppointmentResultVo;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
@RestController
@RequestMapping("appuser/hysfworder")
public class HysfwOrderController {
	private Logger log = LoggerFactory.getLogger(DsfwOrderController.class);
	@Autowired
	private HysfwOrderService hysfwOrderService;
	@Autowired
	private HysfwAppointmentService hysfwAppointmentService;
	@Autowired
	private ServiceService serviceService;
	@Autowired
	private HysfwCommentService hysfwCommentService;
	@Autowired
	private HysfwConferenceTypeService conferenceTypeService;
	@Autowired
	private HysfwConferenceRoomService hysfwConferenceRoomService;
	
	/**
     * 生成会议室订单
     * @param biz
     * @return
     */
    @RequestMapping("/generateOrder")
    public R generateOrder(@RequestParam("files") MultipartFile[] files,HttpServletRequest req,String biz){
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        params.put("uid", req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY));
        
        //判断参会人数数量
        HysfwConferenceRoomEntity hys = hysfwConferenceRoomService.queryObject(params.getInteger("roomid"));
        if(hys.getCapacity()<params.getIntValue("attendnum")){
        	return R.error(1,"参会人数大于会议室容量,请重新预定");
        }
        
        //接口参数
        HysfwOrderEntity order = JSONObject.parseObject(biz,HysfwOrderEntity.class);
        
        //{id:1,count:2}
        JSONArray elist = params.getJSONArray("elist");
        JSONArray slist = params.getJSONArray("slist");
        String equipmentids = "";
        if (ValidateUtil.isValid(elist)) {
		    for (int i = 0; i < elist.size(); i++) {
		    	JSONObject obj = elist.getJSONObject(i);
		    	Integer id = obj.getInteger("id");
		    	Integer count = obj.getInteger("count");
		    	equipmentids += id+"-"+count+",";
			}
		    order.setEquipmentids(equipmentids);
        }
        String serviceids = "";
        if (ValidateUtil.isValid(slist)) {
	        for (int i = 0; i < slist.size(); i++) {
	        	JSONObject obj = slist.getJSONObject(i);
	        	Integer id = obj.getInteger("id");
	        	Integer count = obj.getInteger("count");
	        	serviceids += id+"-"+count+",";
	        }
	        order.setServiceids(serviceids);
        }
        
        String conferencetype = params.getString("conferencetype");
        String conferencename = params.getString("conferencename");
        String attentdleader = params.getString("attentdleader");
        String remark = params.getString("remark");
        Integer attendnum = params.getInteger("attendnum");
        
        //预约信息
        //{date:"2017-10-18",intervalids:"1,2,3"}
        JSONArray appointmentInfo = params.getJSONArray("appointmentInfo");
        
        List<HysfwAppointmentEntity> list = new ArrayList<>();
        for (int i = 0; i < appointmentInfo.size(); i++) {
        	HysfwAppointmentEntity app = appointmentInfo.getObject(i, HysfwAppointmentEntity.class);
        	app.setUid(params.getInteger("uid"));
        	list.add(app);
		}
        
		order.setUid(params.getInteger("uid"));
        order.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_HYSFW));
        JSONObject obj = WebUtils.uploadFiles(files, req, RedisConst.USER_BXWX_ORDERIMG_PRE, RedisConst.UPLOAD_IMG_FILTER);
		order.setImgs(obj.getString("data"));
        R r = hysfwOrderService.generateOrder(order,list);
        log.debug("result:"+r);
        return r;
    }
    
    /**
     * 查询订餐订单
     * @param biz
     * @return
     */
    @RequestMapping("/findOrderList")
    public R findOrderList(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        }else{
            //return R.error(1,"未登录");
        }
        //接口参数
        Integer orderStatus = params.getInteger("orderStatus");
        //params.put("checkstatus", 2);
        params.put("sidx", "createtime");
        params.put("order", "desc");
        Query query = new Query(params);
        
        List<HysfwOrderEntity> orderList = hysfwOrderService.queryList(query);
        List<Map<String,Object>> list = converseToMaplist(orderList,false);
        log.debug("result:"+list);
        return R.ok().put("list", list);
    }
    private List<Map<String, Object>> converseToMaplist(List<HysfwOrderEntity> orderList,boolean isDetail) {
    	ServiceEntity queryService = serviceService.queryService(APPConstant.TYPE_DCFW_GZC);
        String mobile = queryService.getMobile();
    	List<Map<String, Object>> list = new ArrayList<>();
    	for (HysfwOrderEntity order : orderList) {
    		Map<String,Object> resultMap = new HashMap<>();
    		resultMap.put("id", order.getId());
    		resultMap.put("ordercode", order.getOrdercode());
    		resultMap.put("conferencename", order.getConferencename());
    		//商品名称
    		resultMap.put("createtime", order.getCreatetime());
    		resultMap.put("conferencetype", order.getConferencetype());
    		resultMap.put("attendnum", order.getAttendnum());
    		resultMap.put("attentdleader", order.getAttentdleader());
    		resultMap.put("remark", order.getRemark());
    		resultMap.put("serviceMobile", mobile);
    		resultMap.put("status", order.getStatus());
//    		resultMap.put("startDate", order.get);
    		resultMap.put("roomid", order.getRoomid());
    		resultMap.put("checkstatus", order.getCheckstatus());
    		if (order.getStatus()==APPConstant.TYPE_ORDER_STATUS_SERVING) {
    			//判断是否可以加时
    			JSONObject params = new JSONObject();
    			params.put("hours", 1);
    			params.put("date",DateUtils.format(new Date(), "yyyy-MM-dd"));
    			params.put("roomid",order.getRoomid());
    			params.put("uid",order.getUid());
    			HysfwAppointmentResultVo last = isAddable(params);
    			resultMap.put("canAdd", last!=null?1:0);
			}
			//添加预约的列表
			JSONObject params = new JSONObject();
			params.put("uid", order.getUid());
			params.put("oid", order.getId());
			params.put("status", 1);
			List<HysfwAppointmentEntity> alist = hysfwAppointmentService.queryList(params);
			params.put("appointmentInfo", getAppointmentList(alist));
    		list.add(resultMap);
		}
		return list;
	}
    private List<Map<String,Object>> getAppointmentList(List<HysfwAppointmentEntity> list){
    	List<Map<String,Object>> appointmentList = new ArrayList<>();
    	Date lastDate = null;
    	
    	Map<String, Object> map = new HashMap<>();
		for (HysfwAppointmentEntity a : list) {
			List<String> timeList = new ArrayList<>();
			String intervalids = a.getIntervalids();
			String[] strArr = intervalids.split(",");
			for (String str : strArr) {
				timeList.add(HysfwAppointmentServiceImpl.idToDurationMap.get(Integer.parseInt(str)));
			}
			if (map.get("date")!=null) {
				List<String> oldList = (List<String>)map.get("timeList");
				oldList.addAll(timeList);
			}else{
				map = new HashMap<>();
				map.put("date", DateUtils.format(a.getDate(),"yyyy-MM-dd"));
				map.put("timeList", timeList);
				appointmentList.add(map);
			}
			
		}
    	return appointmentList;
    };
    public static void main(String[] args) {
    	HysfwAppointmentEntity a1 = new HysfwAppointmentEntity();
    	a1.setDate(new Date());
    	a1.setIntervalids("1,2,3,6");
    	HysfwAppointmentEntity a2 = new HysfwAppointmentEntity();
    	a2.setDate(new Date());
    	a2.setIntervalids("4,5");
    	List<HysfwAppointmentEntity> arrayList = new ArrayList<>();
    	arrayList.add(a1);
    	arrayList.add(a2);
    	List<Map<String,Object>> appointmentList = new HysfwOrderController().getAppointmentList(arrayList);
    	System.out.println(appointmentList);
	}
    /**
     * 获取订单详情
     * @param biz
     * @return
     */
    @RequestMapping("/getOrderDetail")
    public R getOrderDetail(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        JSONObject params = JSONObject.parseObject(biz);
        //接口参数
        Integer oid = params.getInteger("oid");
        List<HysfwOrderEntity> orderList = hysfwOrderService.queryList(params);
        if (ValidateUtil.isValid(orderList)) {
            List<Map<String, Object>> list = converseToMaplist(orderList,true);
        	Map<String, Object> detail = list.get(0);
            log.debug("result:"+detail);
            return R.ok().put("detail", detail);
        }
        return R.error(1002,"未找到该订单");
    }
//    /**
//     * 取消订单原因列表
//     * @param biz
//     * @return
//     */
//    @RequestMapping("/findReasonList")
//    public R findReasonList(HttpServletRequest req){
//        String biz = req.getParameter("biz");
//        log.debug("biz:"+biz);
//        JSONObject params = JSONObject.parseObject(biz);
//        //查询列表数据
//        List<DcfwGzcOrdercancelReasonEntity> reasonList = dcfwGzcOrdercancelReasonService.queryList(params);
//        log.debug("reasonList:"+reasonList);
//        return R.ok().put("reasonList", reasonList);
//    }
    /**
     * 取消订单
     * @param biz
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
        R r = hysfwOrderService.orderCancel(params);
        return r;
    }

	/**
	 * 获取会议类型
	 * @param biz
	 * @return
	 */
	@RequestMapping("/conferencetype")
	public R conferenceType(HttpServletRequest req){
		log.debug(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"》》》获取会议类型");
		//接口参数
		Map map = new HashMap();
		List<HysfwConferenceTypeEntity> typeEntities = conferenceTypeService.queryList(map);
		return R.ok().put("data",typeEntities);
	}

    /**
     * 添加评价
     * @param biz
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
        HysfwOrderEntity order = hysfwOrderService.queryObject(oid);
        if (order.getStatus()!=APPConstant.TYPE_ORDER_STATUS_FINISHED) {
            return R.error(1002,"非法评论");
        }
        HysfwCommentEntity commentEntity = new HysfwCommentEntity();
        commentEntity.setAnonymous(isAnonymous);
        commentEntity.setContent(content);
        commentEntity.setCreatetime(new Date());
        commentEntity.setOrderid(oid);
        commentEntity.setScore(stars);
        commentEntity.setUid(order.getUid());
        R r = hysfwCommentService.addComment(commentEntity,imgFile);
        return r;
    }
    /**
	 * 删除订单
	 * @param biz
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
		int successNum = hysfwOrderService.invalidOrderBatch(ids.toArray(new Integer[1]));
		int total = ids.size();
		return R.ok().put("successNum", successNum).put("total", total);
	}
	/**
	 * 查询可预约时间
	 * @param req
	 * @return
	 */
	@RequestMapping("/findAvailableTime")
	@IgnoreSign
	@IgnoreToken
	public R findAvailableTime(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		String date = params.getString("date");
		if (DateUtil.parse(date, "yyyy-MM-dd")==null) {
			return R.error(1,"日期格式有误：使用此格式yyyy-MM-dd");
		}
		Set<HysfwAppointmentResultVo> appointmentList = hysfwAppointmentService.findAvailableTime(params);
		return R.ok().put("appointmentList", appointmentList);
	}
//	/**
//	 * 删除我的预约
//	 * @param req
//	 * @return
//	 */
//	@RequestMapping("/deleteMyAppointment")
//	public R deleteMyAppointment(HttpServletRequest req){
//		String biz = req.getParameter("biz");
//		log.debug("biz:"+biz);
//		JSONObject params = JSONObject.parseObject(biz);
//		//添加uid到params
//        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
//        if (ValidateUtil.isValid(uid)) {
//            params.put("uid", uid);
//        }else{
//            //return R.error(1,"未登录");
//        }
//		//接口参数
//		String date = params.getString("date");
//		if (DateUtil.parse(date, "yyyy-MM-dd")==null) {
//			return R.error(1,"日期格式有误：使用此格式yyyy-MM-dd");
//		}
//		//新预约信息
//        //{date:"2017-10-18",intervalids:""}
//        JSONArray appointmentInfo = params.getJSONArray("appointmentInfo");
//        List<HysfwAppointmentEntity> list = new ArrayList<>();
//        for (int i = 0; i < appointmentInfo.size(); i++) {
//        	HysfwAppointmentEntity app = appointmentInfo.getObject(i, HysfwAppointmentEntity.class);
//        	app.setUid(params.getInteger("uid"));
//        	list.add(app);
//		}
//		hysfwAppointmentService.clear(params);
//		return R.ok().put("appointmentList", appointmentList);
//	}
	/**
	 * 我的预约加时
	 * @param req
	 * @return
	 */
	@RequestMapping("/addHours")
	public R addHours(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        }else{
            //return R.error(1,"未登录");
        }
        Integer oid = params.getInteger("oid");
        Integer hours = params.getInteger("hours");
        Integer roomid = params.getInteger("roomid");
        params.put("date", DateUtils.format(new Date(), "yyyy-MM-dd"));
        HysfwAppointmentEntity appointmentEntity = new HysfwAppointmentEntity();
        appointmentEntity.setDate(new Date());
        appointmentEntity.setOrderid(oid);
        appointmentEntity.setRoomid(roomid);
        appointmentEntity.setStatus(1);
        appointmentEntity.setUid(params.getInteger("uid"));
        HysfwAppointmentResultVo last = isAddable(params);
        if (last==null) {
			return R.error(1,"加时失败");
		}
        String intervalids = "";
        Integer lastid = last.getIntervalid();
        for (int i = 0; i < hours; i++) {
        	lastid++;
        	if (i==hours-1) {
        		intervalids += lastid;
			}else{
				intervalids += lastid+",";
			}
		}
        appointmentEntity.setIntervalids(intervalids);
        hysfwAppointmentService.save(appointmentEntity);
        return R.ok();
	}
	private HysfwAppointmentResultVo isAddable(JSONObject params){
		Integer hours = params.getInteger("hours");
		Set<HysfwAppointmentResultVo> appointmentList = hysfwAppointmentService.findAvailableTime(params);
		HysfwAppointmentResultVo last = null;
        for (HysfwAppointmentResultVo resultVo : appointmentList) {
			if (resultVo.getStatus()==APPConstant.TYPE_HYSFW_APPOINTMENT_SELF) {
				last = resultVo;
			}
		}
        if (last==null) return null;
        int availableHours = 0;
        for (HysfwAppointmentResultVo resultVo : appointmentList) {
			if (resultVo.getIntervalid()>last.getIntervalid()) {
				if (resultVo.getStatus()==APPConstant.TYPE_HYSFW_APPOINTMENT_SELF||resultVo.getStatus()==APPConstant.TYPE_HYSFW_APPOINTMENT_UNAVAILABLE) {
					break;
				}
				availableHours++;
			}
		}
        if (hours>availableHours) {
			return null;
		}
        return last;
	}
}
