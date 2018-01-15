package com.lcworld.controller;

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
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.TGxdfwCommentEntity;
import com.lcworld.entity.TGxdfwOrderEntity;
import com.lcworld.entity.TGxdfwOrdercancelReasonEntity;
import com.lcworld.entity.TGxdfwOrderdetailEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.ServiceService;
import com.lcworld.service.TGxdfwClothestypeService;
import com.lcworld.service.TGxdfwCommentService;
import com.lcworld.service.TGxdfwOrderService;
import com.lcworld.service.TGxdfwOrdercancelReasonService;
import com.lcworld.service.TGxdfwOrderdetailService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.WebUtils;
import com.lcworld.utils.util.ValidateUtil;

import sun.awt.AppContext;


/**
 * 干洗店服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:53
 */
@RestController
@RequestMapping("appuser/tgxdfworder")
public class TGxdfwOrderController {
    private Logger log = LoggerFactory.getLogger(TGxdfwOrderController.class);
    @Autowired
    private TGxdfwOrderService tGxdfwOrderService;
    @Autowired
    private TGxdfwClothestypeService tGxdfwClothestypeService;
    @Autowired
    private TGxdfwOrderdetailService tGxdfwOrderdetailService;
    @Autowired
    private TGxdfwOrdercancelReasonService tGxdfwOrdercancelReasonService;
    @Autowired
    private TGxdfwCommentService tGxdfwCommentService;
    @Autowired
    private ServiceService serviceService;
    /**
     * 生成干洗订单
     * @param biz
     * @return
     */
    @RequestMapping("/generateOrder")
    public R generateOrder(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Integer uid = WebUtils.getUid(req);
        params.put("uid", uid);
        //生成订单
        R r = tGxdfwOrderService.generateOrder(params);
        return r;
    }
    /**
     * 查询干洗订单
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
        if (orderStatus.intValue()==APPConstant.TYPE_ORDER_STATUS_FINISHED) {
			params.remove("orderStatus");
			params.put("finishStatus","finishStatus");
		}
        Query query = new Query(params);
        List<TGxdfwOrderEntity> orderList = tGxdfwOrderService.queryList(query);
        List<Map<String,Object>> list = converseToMaplist(orderList,false);
        log.debug("result:"+list);
        return R.ok().put("list", list);
    }
    private List<Map<String, Object>> converseToMaplist(List<TGxdfwOrderEntity> orderList,boolean isDetail) {
    	ServiceEntity queryService = serviceService.queryService(APPConstant.TYPE_GXDFW);
        String mobile = queryService.getMobile();
    	List<Map<String, Object>> list = new ArrayList<>();
    	for (TGxdfwOrderEntity order : orderList) {
    		Map<String,Object> resultMap = new HashMap<>();
    		resultMap.put("id", order.getId());
    		resultMap.put("ordercode", order.getCode());
    		resultMap.put("status", order.getStatus());
    		resultMap.put("payStatus", order.getPayStatus());
    		resultMap.put("createtime", order.getCreatetime());
    		resultMap.put("status", order.getStatus());
    		resultMap.put("totalprice", order.getTotalprice());
    		resultMap.put("ordertype", APPConstant.TYPE_GXDFW);
    		resultMap.put("checkstatus", order.getCheckstatus1());
    		ArrayList<Map<String,Object>> clothesList = new ArrayList<>();
    		List<Map<String,String>> remarklist = new ArrayList<Map<String,String>>();
    		for (TGxdfwOrderdetailEntity detail : order.getDetailList()) {
    			HashMap<String,Object> detailMap = new HashMap<>();
    			detailMap.put("clothesName", detail.getClothName());
    			detailMap.put("count", detail.getCount());
    			detailMap.put("price", detail.getPrice());
    			detailMap.put("totalprice", detail.getTotalprice());
    			detailMap.put("remark", detail.getRemark());
    			if(ValidateUtil.isValid(detail.getRemark())){
    			    remarklist.add(createMap(detail.getRemark(),detail.getClothName()));
    			}
    			clothesList.add(detailMap);
			}
    		resultMap.put("clothesList",clothesList);
    		resultMap.put("remarklist", remarklist);
    		resultMap.put("serviceMobile", mobile);
    		if (isDetail) {
    			resultMap.put("totalprice", order.getTotalprice());
    			resultMap.put("paytype", order.getPayType());
    			resultMap.put("logStatus", order.getLogStatus());
			}
    		resultMap.put("isEvaluated", order.getLogStatus()==APPConstant.TYPE_GXDFW_LOG_STATUS_EVALUATED?1:0);
    		list.add(resultMap);
		}
		return list;
	}
    private Map<String, String> createMap(String remark, String clothName) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("name", clothName);
        map.put("remark", remark);
        return map;
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
        List<TGxdfwOrderEntity> orderList = tGxdfwOrderService.queryList(params);
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
     * @param biz
     * @return
     */
    @RequestMapping("/findReasonList")
    public R findReasonList(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        JSONObject params = JSONObject.parseObject(biz);
        //查询列表数据
        List<TGxdfwOrdercancelReasonEntity> reasonList = tGxdfwOrdercancelReasonService.queryList(params);
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
        R r = tGxdfwOrderService.orderCancel(params);
        return r;
    }
    /**
     * 添加评价
     * @param biz
     * @return
     * @throws Exception 
     */
    @RequestMapping("/addComment")
    public R addComment(HttpServletRequest req,@RequestParam("imgFile") MultipartFile[] imgFile) throws Exception{
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        JSONObject params = JSONObject.parseObject(biz);
        //接口参数
        Integer oid = params.getInteger("oid");
        Double stars = params.getDouble("stars");
        String content = params.getString("content");
        Integer isAnonymous = params.getInteger("isAnonymous");
        TGxdfwOrderEntity order = tGxdfwOrderService.queryObject(oid);
        if (order.getStatus()!=APPConstant.TYPE_ORDER_STATUS_FINISHED&&order.getPayStatus()!=APPConstant.TYPE_ORDER_PAY_STATUS_PAYED) {
            return R.error(1002,"非法评论");
        }
        TGxdfwCommentEntity commentEntity = new TGxdfwCommentEntity();
        commentEntity.setAnonymous(isAnonymous);
        commentEntity.setContent(content);
        commentEntity.setCreatetime(new Date());
        commentEntity.setOrderid(oid);
        commentEntity.setScore(stars);
        commentEntity.setUid(order.getUid());
        R r = tGxdfwCommentService.addComment(commentEntity,imgFile);
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
		int successNum = tGxdfwOrderService.invalidOrderBatch(ids.toArray(new Integer[1]));
		int total = ids.size();
		return R.ok().put("successNum", successNum).put("total", total);
	}
}
