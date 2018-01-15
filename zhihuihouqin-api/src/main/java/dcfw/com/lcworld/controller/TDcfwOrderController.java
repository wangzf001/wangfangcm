package com.lcworld.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import com.lcworld.entity.DcfwGzcOrderEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.entity.TDcfwCommentEntity;
import com.lcworld.entity.TDcfwGetfoodtimeEntity;
import com.lcworld.entity.TDcfwOrderEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.ServiceService;
import com.lcworld.service.TDcfwCommentService;
import com.lcworld.service.TDcfwFoodService;
import com.lcworld.service.TDcfwGetfoodtimeService;
import com.lcworld.service.TDcfwOrderService;
import com.lcworld.service.TDcfwOrderdetailService;
import com.lcworld.utils.DateUtils;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.util.ValidateUtil;



/**
 * 订餐服务订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
@RestController
@RequestMapping("appuser/tdcfworder")
public class TDcfwOrderController {
    private Logger log = LoggerFactory.getLogger(TYytcMealController.class);
    @Autowired
    private TDcfwOrderService tDcfwOrderService;
    @Autowired
    private TDcfwGetfoodtimeService tDcfwGetfoodtimeService;
    @Autowired
    private TDcfwOrderdetailService tDcfwOrderdetailService;
    @Autowired
    private TDcfwFoodService tDcfwFoodService;
    @Autowired
    private TDcfwCommentService tDcfwCommentService;
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
        List<TDcfwGetfoodtimeEntity> timeList = tDcfwGetfoodtimeService.queryList(params);
        List<TDcfwGetfoodtimeEntity> amList = new ArrayList<>();
        List<TDcfwGetfoodtimeEntity> pmList = new ArrayList<>();
        for (int i = 0; i < timeList.size(); i++) {
            TDcfwGetfoodtimeEntity time = timeList.get(i);
            time.setTimeStr(time.getStarttime()+"-"+time.getEndtime());
            if (time.getPeriodtype().intValue()==1) {
                amList.add(time);
            }else{
                pmList.add(time);
            }
        }
        Comparator<TDcfwGetfoodtimeEntity> comparator = new Comparator<TDcfwGetfoodtimeEntity>() {
            @Override
            public int compare(TDcfwGetfoodtimeEntity o1, TDcfwGetfoodtimeEntity o2) {
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
    public R generateOrder(HttpServletRequest req){
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
        String realname = params.getString("realname");
        String mobile = params.getString("mobile");
        Integer getfoodtime = params.getInteger("getfoodtimeID");
        JSONArray foodList = params.getJSONArray("foodList");
        //foodList=[{foodid:1,count:2}]
        R r = tDcfwOrderService.generateOrder(params);
        log.debug("result:"+r);
        return r;
    }
    /**
     * 查询订餐订单
     * @param biz
     * @return
     */
    @RequestMapping("/findOrderList")
    public R findOrderList(HttpServletRequest req,String biz){
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        params.put("uid", req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY));
        //接口参数
        Integer orderStatus = params.getInteger("orderStatus");
        if (orderStatus.intValue()==APPConstant.TYPE_ORDER_STATUS_FINISHED) {
			params.remove("orderStatus");
			params.put("finishStatus","finishStatus");
		}
        params.put("sidx", "o.createtime");
        params.put("order", "desc");
        Query query = new Query(params);
        List<TDcfwOrderEntity> orderList = tDcfwOrderService.queryList(query);
        List<Map<String,Object>> list = converseToMaplist(orderList,false);
        log.debug("result:"+list);
        return R.ok().put("list", list);
    }
    private List<Map<String, Object>> converseToMaplist(List<TDcfwOrderEntity> orderList,boolean isDetail) {
    	ServiceEntity queryService = serviceService.queryService(APPConstant.TYPE_DCFW);
        String mobile = queryService.getMobile();
    	List<Map<String, Object>> list = new ArrayList<>();
    	for (TDcfwOrderEntity order : orderList) {
    		Map<String,Object> resultMap = new HashMap<>();
    		resultMap.put("id", order.getId());
    		resultMap.put("ordercode", order.getOrdercode());
    		resultMap.put("foodImg", order.getDetailList().get(0).getFoodImg());
    		resultMap.put("foodName", order.getDetailList().get(0).getFoodName());
    		resultMap.put("foodOriginal", order.getDetailList().get(0).getFoodOriginal());
    		resultMap.put("payStatus", order.getPayStatus());
    		resultMap.put("status", order.getStatus());
    		resultMap.put("serviceMobile", mobile);
    		resultMap.put("ordertype", APPConstant.TYPE_DCFW);
    		resultMap.put("count", order.getDetailList().get(0).getCount());
    		resultMap.put("totalprice", order.getTotalprice());
    		if (isDetail) {
    			resultMap.put("taketime", order.getTakeTime());
    			resultMap.put("price", order.getDetailList().get(0).getPrice());
    			resultMap.put("totalprice", order.getTotalprice());
    			resultMap.put("createtime", order.getCreatetime());
    			resultMap.put("paytype", order.getPayType());
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
        List<TDcfwOrderEntity> orderList = tDcfwOrderService.queryList(params);
        if (ValidateUtil.isValid(orderList)) {
        	List<Map<String, Object>> list = converseToMaplist(orderList,true);
        	Map<String, Object> detail = list.get(0);
            log.debug("result:"+detail);
            return R.ok().put("detail", detail);
        }
        return R.error();
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
        R r = tDcfwOrderService.orderCancel(params);
        return r;
    }
    /**
     * 添加评价
     * @param biz
     * @return
     * @throws IOException 
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
        TDcfwOrderEntity order = tDcfwOrderService.queryObject(oid);
        if (order.getStatus()!=APPConstant.TYPE_ORDER_STATUS_FINISHED&&order.getPayStatus()!=APPConstant.TYPE_ORDER_PAY_STATUS_PAYED) {
            return R.error(1002,"非法评论");
        }
        TDcfwCommentEntity commentEntity = new TDcfwCommentEntity();
        commentEntity.setAnonymous(isAnonymous);
        commentEntity.setContent(content);
        commentEntity.setCreatetime(new Date());
        commentEntity.setOrderid(oid);
        commentEntity.setScore(stars);
        commentEntity.setSupportid(order.getUid());
        commentEntity.setUid(order.getUid());
        R r = tDcfwCommentService.addComment(commentEntity,imgFile);
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
		int successNum = tDcfwOrderService.invalidOrderBatch(ids.toArray(new Integer[1]));
		int total = ids.size();
		return R.ok().put("successNum", successNum).put("total", total);
	}
	/**
	 * 判断是否可以预定
	 * @param biz
	 * @return
	 */
	@RequestMapping("/canOrdered")
	public R canOrdered(HttpServletRequest req){
		String biz = req.getParameter("biz");
		log.debug("biz:"+biz);
		JSONObject params = JSONObject.parseObject(biz);
		//接口参数
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		int nowweekday = c.get(Calendar.DAY_OF_WEEK)-1;
		c.set(1970, 0, 1);
		Date time1970 = c.getTime();
		//默认参数
		ArrayList<Integer> aweekdays = new ArrayList<>();
		aweekdays.add(3);
		aweekdays.add(5);
		ArrayList<Date> timeList = new ArrayList<>();
		timeList.add(DateUtils.parse("12:00", "HH:mm"));
		timeList.add(DateUtils.parse("13:30", "HH:mm"));
		ServiceEntity service = serviceService.queryService(APPConstant.TYPE_DCFW);
		String dcfwtokenordertime = service.getDcfwtokenordertime();
		if (ValidateUtil.isValid(dcfwtokenordertime)) {
			try {
				String[] split = dcfwtokenordertime.split("-");
				String weekdaystr = split[0];
				String timestr = split[1];
				String[] weekdayArr = weekdaystr.split("\\+");
				aweekdays.clear();
				//每周可预约时间
				for (String weekday : weekdayArr) {
					aweekdays.add(Integer.parseInt(weekday));
				}
				//每日可预约时间
				String[] timeArr = timestr.split("~");
				timeList.clear();
				for (String time : timeArr) {
					timeList.add(DateUtils.parse(time, "HH:mm"));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		if (aweekdays.contains(nowweekday)) {
			if (time1970.getTime()>=timeList.get(0).getTime()&&time1970.getTime()<=timeList.get(1).getTime()) {
				return R.ok("可以预定").put("status", 1);
			}
		}
		String weekdays = "";
		for (int i = 0; i < aweekdays.size(); i++) {
			String weekday = getWeekday(aweekdays.get(i));
			if (i==aweekdays.size()-1) {
				weekdays += weekday;
			}else{
				weekdays += weekday+"、";
			}
		}
		String timestr = "";
		for (int i = 0; i < timeList.size(); i++) {
			if (i==timeList.size()-1) {
				timestr += DateUtils.format(timeList.get(i), "HH:mm");
			}else{
				timestr += DateUtils.format(timeList.get(i), "HH:mm")+"到";
			}
		}
		return R.ok("非预定时间段").put("status", 0).put("rule", "每"+weekdays+"中午 "+timestr+" 接受净菜预定。"+DateUtils.format(timeList.get(1), "HH:mm")+"后不接受预定。");
	}
	String getWeekday(int day){
		switch (day) {
		case 1:
			return "周一";
		case 2:
			return "周二";
		case 3:
			return "周三";
		case 4:
			return "周四";
		case 5:
			return "周五";
		case 6:
			return "周六";
		case 7:
			return "周末";
		default:
			return "";
		}
	}
	/**
	 * 获取规则
	 * @param biz
	 * @return
	 */
	@RequestMapping("/getRule")
	public R getRule(HttpServletRequest req){
		Map<String,Object> params = new HashMap<>();
		
		List<TDcfwGetfoodtimeEntity> timeList = tDcfwGetfoodtimeService.queryList(params);
		TDcfwGetfoodtimeEntity time = null;
		if (ValidateUtil.isValid(timeList)) {
			time = timeList.get(0);
		}
		
		ServiceEntity service = serviceService.queryService(APPConstant.TYPE_DSFW);
		String dcfwtokenordertime = service.getDcfwtokenordertime();
		Calendar c = Calendar.getInstance();
		if (ValidateUtil.isValid(dcfwtokenordertime)) {
			try {
				String[] split = dcfwtokenordertime.split("-");
				String timestr = split[1];
				//每日可预约时间
				String[] timeArr = timestr.split("~");
				String end = timeArr[1];
				Date endtime = DateUtils.parse(end, "HH:mm");
				c.setTime(endtime);
				c.add(Calendar.MINUTE, 10);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return R.ok().put("ruleToken", "每周三、周五下午"+time.getTimeStr()+"取菜。").put("ruleCancel", "当天中午"+DateUtils.format(c.getTime(), "HH:mm")+"后不接受退单。");
	}
	public static void main(String[] args) {
		String[] weekdayArr = "1+2+3+4".split("\\+");
		System.out.println(Arrays.toString(weekdayArr));
	}
}
