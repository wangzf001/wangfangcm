package com.lcworld.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.BgypfwOrderEntity;
import com.lcworld.entity.DcfwGzcCommentEntity;
import com.lcworld.entity.DcfwGzcOrderEntity;
import com.lcworld.entity.DcfwGzcOrdercancelReasonEntity;
import com.lcworld.entity.DcfwGzcSendfoodtimeEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.TUserEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.DcfwGzcCommentService;
import com.lcworld.service.DcfwGzcOrderService;
import com.lcworld.service.DcfwGzcOrdercancelReasonService;
import com.lcworld.service.DcfwGzcSendfoodtimeService;
import com.lcworld.service.ServiceService;
import com.lcworld.service.TUserService;
import com.lcworld.utils.DateUtil;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;


/**
 * 订餐服务-工作餐订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 13:35:13
 */
@RestController
@RequestMapping("appuser/dcfwgzcorder")
public class DcfwGzcOrderController {
    private Logger log = LoggerFactory.getLogger(DcfwGzcOrderController.class);
    @Autowired
    private DcfwGzcOrderService dcfwGzcOrderService;
    @Autowired
    private DcfwGzcSendfoodtimeService dcfwGzcSendfoodtimeService;
    @Autowired
    private DcfwGzcOrdercancelReasonService dcfwGzcOrdercancelReasonService;
    @Autowired
    private DcfwGzcCommentService dcfwGzcCommentService;
    @Autowired
    private ServiceService serviceService;
    
    /**
     * 获取取餐时间
     * @param biz
     * @return
     */
    @RequestMapping("/getFoodTimeList")
    public R getFoodTimeList(HttpServletRequest req){
        String biz = req.getParameter("biz");
        log.debug("biz:"+biz);
        JSONObject params = JSONObject.parseObject(biz);
        //接口参数
        //查询列表数据
        List<DcfwGzcSendfoodtimeEntity> timeList = dcfwGzcSendfoodtimeService.queryList(params);
        List<DcfwGzcSendfoodtimeEntity> amList = new ArrayList<>();
        List<DcfwGzcSendfoodtimeEntity> pmList = new ArrayList<>();
        for (int i = 0; i < timeList.size(); i++) {
            DcfwGzcSendfoodtimeEntity time = timeList.get(i);
            time.setTimeStr(time.getStarttime()+"-"+time.getEndtime());
            if (time.getPeriodtype().intValue()==1) {
                amList.add(time);
            }else{
                pmList.add(time);
            }
        }
        Comparator<DcfwGzcSendfoodtimeEntity> comparator = new Comparator<DcfwGzcSendfoodtimeEntity>() {
            @Override
            public int compare(DcfwGzcSendfoodtimeEntity o1, DcfwGzcSendfoodtimeEntity o2) {
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
     * 生成订餐订单
     * @param biz
     * @return
     */
    @RequestMapping("/generateOrder")
    public R generateOrder(HttpServletRequest req,String biz){
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        params.put("uid", req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY));
        
        R r = dcfwGzcOrderService.generateOrder(params);
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
        Query query = new Query(params);
        List<DcfwGzcOrderEntity> orderList = dcfwGzcOrderService.queryList(query);
        List<Map<String,Object>> list = converseToMaplist(orderList,false);
        log.debug("result:"+list);
        return R.ok().put("list", list);
    }
    private List<Map<String, Object>> converseToMaplist(List<DcfwGzcOrderEntity> orderList,boolean isDetail) {
    	ServiceEntity queryService = serviceService.queryService(APPConstant.TYPE_DCFW_GZC);
        String mobile = queryService.getMobile();
    	List<Map<String, Object>> list = new ArrayList<>();
    	for (DcfwGzcOrderEntity order : orderList) {
    		Map<String,Object> resultMap = new HashMap<>();
    		resultMap.put("id", order.getId());
    		resultMap.put("ordercode", order.getOrdercode());
    		resultMap.put("count", order.getCount());
    		//商品名称
    		resultMap.put("createtime", order.getCreatetime());
    		resultMap.put("sendTime", order.getSendTime());
    		resultMap.put("payStatus", order.getPayStatus());
    		resultMap.put("serviceMobile", mobile);
    		resultMap.put("status", order.getStatus());
    		resultMap.put("type", order.getType());
    		if (isDetail) {
    			resultMap.put("fixedmealenddate", order.getFixedmealenddate());
    			resultMap.put("fxedmealstartdate", order.getFixedmealstartdate());
    			resultMap.put("fixedmealtype", order.getFixedmealtype());
    			resultMap.put("totalprice", order.getTotalprice());
    			resultMap.put("address", order.getAddress());
    			resultMap.put("sendTime", order.getSendTime());
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
        List<DcfwGzcOrderEntity> orderList = dcfwGzcOrderService.queryList(params);
        if (ValidateUtil.isValid(orderList)) {
            List<Map<String, Object>> list = converseToMaplist(orderList,true);
        	Map<String, Object> detail = list.get(0);
            log.debug("result:"+detail);
            return R.ok().put("detail", detail);
        }
        return R.error(1002,"未找到该订单");
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
        List<DcfwGzcOrdercancelReasonEntity> reasonList = dcfwGzcOrdercancelReasonService.queryList(params);
        log.debug("reasonList:"+reasonList);
        return R.ok().put("reasonList", reasonList);
    }
}
