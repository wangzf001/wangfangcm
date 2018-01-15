package com.lcworld.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.domain.OrderDetailResult;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.BgypfwCommentEntity;
import com.lcworld.entity.BgypfwOrderEntity;
import com.lcworld.entity.BgypfwOrdercancelReasonEntity;
import com.lcworld.entity.DsfwOrderEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.BgypfwCommentService;
import com.lcworld.service.BgypfwOrderService;
import com.lcworld.service.BgypfwOrdercancelReasonService;
import com.lcworld.service.BgypfwOrderdetailService;
import com.lcworld.service.DeliveryOrderService;
import com.lcworld.service.ServiceService;
import com.lcworld.utils.DateUtil;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 办公用品服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
@RestController
@RequestMapping("appuser/bgypfworder")
public class BgypfwOrderController {
	private Logger log = LoggerFactory.getLogger(BgypfwOrderController.class);
	@Autowired
	private BgypfwOrderService bgypfwOrderService;
	@Autowired
	private BgypfwOrdercancelReasonService bgypfwOrdercancelReasonService;
	@Autowired
	private BgypfwCommentService bgypfwCommentService;
	@Autowired
	private BgypfwOrderdetailService bgypfwOrderdetailService;
	@Autowired
	private ServiceService srviceService;
	@Autowired
	private DeliveryOrderService deliveryOrderService;
	/**
	 * 生成订单
	 * @param biz
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/generateOrder")
	public R generateOrder(HttpServletRequest req) throws ParseException{
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
		Integer addressid = params.getInteger("addressid");
		String expectedTimeStr = params.getString("expectedTime");
		Date expectedTime = DateUtil.parse(expectedTimeStr, "yyyy-MM-dd hh:mm");
		JSONArray productList = params.getJSONArray("productList");
		//productList=[{skuid=1,count=1}]
		String remark = params.getString("remark");
		R r = bgypfwOrderService.generateOrder(params);
		return r;
	}
	/**
	 * 查询订单
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findOrderList")
	public R findOrderList(HttpServletRequest req,String biz){
		//查询列表数据
		JSONObject params = JSONObject.parseObject(biz);
		//添加uid到params
		params.put("uid", req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY));
		params.put("ordertype", APPConstant.TYPE_BGYPFW);
		
		//接口参数
		params.put("sidx", "o.createtime");
        params.put("order", "desc");
		Query query = new Query(params);
		List<BgypfwOrderEntity> orderList = bgypfwOrderService.queryList(query);
		List<Map<String,Object>> list = converseToMaplist(orderList,false);
		log.debug("result:"+list);
		return R.ok().put("list", list);
	}
	private List<Map<String, Object>> converseToMaplist(List<BgypfwOrderEntity> orderList,boolean isDetail) {
    	ServiceEntity queryService = srviceService.queryService(APPConstant.TYPE_BGYPFW);
        String mobile = queryService.getMobile();
    	List<Map<String, Object>> list = new ArrayList<>();
    	for (BgypfwOrderEntity order : orderList) {
    		Map<String,Object> resultMap = new HashMap<>();
    		resultMap.put("id", order.getId());
    		resultMap.put("ordercode", order.getCode());
    		resultMap.put("paystatus", order.getPayStatus());
    		resultMap.put("img", order.getOrderDetailList().get(0).getSku().getMainimg());
    		resultMap.put("skuname", order.getOrderDetailList().get(0).getSku().getSkuname());
    		resultMap.put("gg", order.getOrderDetailList().get(0).getSku().getGg());
    		resultMap.put("productid", order.getOrderDetailList().get(0).getProductid());
    		resultMap.put("count", order.getOrderDetailList().get(0).getCount());
    		//商品名称
    		resultMap.put("productName", order.getOrderDetailList().get(0).getProductname());
    		resultMap.put("suppliername", order.getOrderDetailList().get(0).getSuppliername());
    		resultMap.put("status", order.getStatus());
    		resultMap.put("payStatus", order.getPayStatus());
    		resultMap.put("serviceMobile", mobile);
    		resultMap.put("ordertype", APPConstant.TYPE_BGYPFW);
    		resultMap.put("price", order.getOrderDetailList().get(0).getPrice());
    		if (order.getStatus().intValue()==2) {
				//有配送人员
    			resultMap.put("dusername", order.getDusername());
    			resultMap.put("dmobile", order.getDmobile());
    			resultMap.put("dphoto", order.getDphoto());
			}
    		if (isDetail) {
    			resultMap.put("sendTime", order.getExpertarrivaltme()!=null?DateUtil.dateToStr(order.getExpertarrivaltme()):"立即配送");
    			resultMap.put("receiverAddress", order.getAddress().getAddress());
    			resultMap.put("receiverName", order.getAddress().getRealname());
    			resultMap.put("receiverMobile", order.getAddress().getMobile());
    			resultMap.put("createtime", order.getCreatetime());
    			resultMap.put("paytype", order.getPaytype());
    			resultMap.put("totalprice", order.getTotalprice());
			}
    		list.add(resultMap);
		}
		return list;
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
		params.put("ordertype", APPConstant.TYPE_BGYPFW);
		List<BgypfwOrderEntity> orderList = bgypfwOrderService.queryList(params);
		
		if (ValidateUtil.isValid(orderList)) {
			List<Map<String, Object>> list = converseToMaplist(orderList,true);
        	Map<String, Object> detail = list.get(0);
            log.debug("result:"+detail);
            return R.ok().put("detail", detail);
		}
		return R.error();
	}
	/**
	 * 取消订单原因列表
	 * @param biz
	 * @return
	 */
	@RequestMapping("/findReasonList")
	public R findReasonList(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//查询列表数据
		List<BgypfwOrdercancelReasonEntity> reasonList = bgypfwOrdercancelReasonService.queryList(params);
		log.debug("reasonList:"+reasonList);
		return R.ok().put("reasonList", reasonList);
	}
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
		R r = bgypfwOrderService.orderCancel(params);
		return r;
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
		//添加uid到params
		Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
		if (ValidateUtil.isValid(uid)) {
			params.put("uid", uid);
		}else{
			//return R.error(1,"未登录");
		}
		//接口参数
		Integer oid = params.getInteger("oid");
		Integer productId = params.getInteger("productId");
		Double stars = params.getDouble("stars");
		String content = params.getString("content");
		Integer isAnonymous = params.getInteger("isAnonymous");
		BgypfwOrderEntity order = bgypfwOrderService.queryObject(oid);
		if (order.getStatus()!=APPConstant.TYPE_ORDER_STATUS_FINISHED&&order.getPayStatus()!=APPConstant.TYPE_ORDER_PAY_STATUS_PAYED) {
			return R.error(1002,"非法评论");
		}
		BgypfwCommentEntity commentEntity = new BgypfwCommentEntity();
		commentEntity.setAnonymous(isAnonymous);
		commentEntity.setContent(content);
		commentEntity.setCreatetime(new Date());
		commentEntity.setOrderid(oid);
		commentEntity.setScore(stars);
		commentEntity.setUid(params.getInteger("uid"));
		commentEntity.setProductid(productId);
		R r = bgypfwCommentService.addComment(commentEntity,imgFile);
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
		int successNum = bgypfwOrderService.invalidOrderBatch(ids.toArray(new Integer[1]));
		int total = ids.size();
		return R.ok().put("successNum", successNum).put("total", total);
	}
}
