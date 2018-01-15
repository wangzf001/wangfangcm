package com.lcworld.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.UserCfEntity;
import com.lcworld.entity.UserCfczOrderEntity;
import com.lcworld.entity.UserWalleorderEntity;
import com.lcworld.interceptor.TokenCheckInterceptor;
import com.lcworld.service.UserCfService;
import com.lcworld.service.UserCfczOrderService;
import com.lcworld.utils.DateUtil;
import com.lcworld.utils.OrderCodeGenerator;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 用户餐卡充值订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-23 11:08:21
 */
@RestController
@RequestMapping("appuser/usercfczorder")
public class UserCfczOrderController {
	@Autowired
	private UserCfczOrderService userCfczOrderService;
	@Autowired
	private UserCfService userCfService;
	/**
     * 生成餐费充值订单
     * @param req
     * @return
     */
    @RequestMapping("/generateChargeOrder")
    public R generateChargeOrder(HttpServletRequest req){
    	String biz = req.getParameter("biz");
        //查询列表数据
        JSONObject params = JSONObject.parseObject(biz);
        //添加uid到params
        Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
        if (ValidateUtil.isValid(uid)) {
            params.put("uid", uid);
        }else{
            //return R.error(1,"未登录");
        }
        UserCfczOrderEntity order = JSONObject.parseObject(biz, UserCfczOrderEntity.class);
        order.setCreatetime(new Date());
        order.setOrdercode(OrderCodeGenerator.createOrderCode(APPConstant.TYPE_CFCZ));
        order.setStatus(APPConstant.TYPE_CFCZ_ORDER_STATUS_ORDERED);
        order.setUid(params.getInteger("uid"));
        userCfczOrderService.save(order);
        return R.ok().put("ordercode", order.getOrdercode()).put("ordertype", APPConstant.TYPE_CFCZ).put("createtime", order.getCreatetime()).put("paytype", order.getPaytype()).put("totalMoney", order.getMoney());
    }
    /**
     * 查询充值订单
     * @param req
     * @return
     */
    @RequestMapping("/findChargeOrder")
    public R findChargeOrder(HttpServletRequest req){
    	String biz = req.getParameter("biz");
    	//查询列表数据
    	JSONObject params = JSONObject.parseObject(biz);
    	//添加uid到params
    	Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
    	if (ValidateUtil.isValid(uid)) {
    		params.put("uid", uid);
    	}else{
    		//return R.error(1,"未登录");
    	}
    	//参数
    	Integer isUnderway = params.getInteger("isUnderway");
    	params.put("sidx", "o.createtime");
    	params.put("order", "desc");
    	Query query = new Query(params);
    	List<UserCfczOrderEntity> list = userCfczOrderService.queryList(query);
    	List<Map<String,Object>> outList = groupByDate(list);
    	return R.ok().put("list", outList);
    }
    private List<Map<String, Object>> groupByDate(List<UserCfczOrderEntity> walletLogList) {
    	Calendar c = Calendar.getInstance();
    	Calendar d = Calendar.getInstance();
    	d.setTime(new Date());
    	SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
//    	Integer preMonth = -1;
//    	Map<String,Object> map;
    	List<Map<String,Object>> list = new ArrayList<>();
//    	List<Map<String,Object>> innerList = new ArrayList<>();
    	for (UserCfczOrderEntity order : walletLogList) {
    		Map<String,Object> innerMap = new HashMap<>();
    		Date createtime = order.getCreatetime();
    		c.setTime(createtime);
	    	Integer month = c.get(Calendar.MONTH)+1;
//	    	if (preMonth.intValue()!=month.intValue()) {
//	    		map = new HashMap<>();
//	    		innerList = new ArrayList<>();
	    		String yearMonth = "";
	    		int nowYear = d.get(Calendar.YEAR);
	    		int oldYear = c.get(Calendar.YEAR);
	    		if (nowYear==oldYear) {
					//今年
	    			if (d.get(Calendar.MONTH)==c.get(Calendar.MONTH)) {
						//本月
	    				yearMonth = "本月";
					}else{
						yearMonth = ""+(c.get(Calendar.MONTH)+1)+"月";
					}
				}else{
					yearMonth = ""+c.get(Calendar.YEAR)+"年"+(c.get(Calendar.MONTH)+1)+"月";
				}
	    		innerMap.put("monthName", yearMonth);
//	    		map.put("monthList", innerList);
//	    		list.add(map);
//	    		//使相等
//	    		preMonth = month;
//	    	}
	    	innerMap.put("createtime", createtime);
	    	innerMap.put("weekday", getChineseWeek(DateUtil.getDayOfWeek(createtime)));
	    	innerMap.put("monthDay", sdf.format(createtime));
	    	innerMap.put("paytype", order.getPaytype());
	    	innerMap.put("money", order.getMoney());
	    	innerMap.put("status", order.getStatus());
	    	list.add(innerMap);
		}
		return list;
	}
	String getChineseWeek(int weekday){
    	switch (weekday) {
		case 1:return "周一";
		case 2:return "周二";
		case 3:return "周三";
		case 4:return "周四";
		case 5:return "周五";
		case 6:return "周六";
		case 7:return "周日";
		default: return "";
		}
    };
    /**
     * 查询最后充值卡号
     * @param req
     * @return
     */
    @RequestMapping("/findLastCardNum")
    public R findLastCardNum(HttpServletRequest req){
    	String biz = req.getParameter("biz");
    	//查询列表数据
    	JSONObject params = JSONObject.parseObject(biz);
    	//添加uid到params
    	Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
    	if (ValidateUtil.isValid(uid)) {
    		params.put("uid", uid);
    	}else{
    		//return R.error(1,"未登录");
    	}
    	params.put("sidx", "o.createtime");
    	params.put("order", "DESC");
    	Query query = new Query(params);
    	query.setLimit(1);
    	query.setPage(1);
    	UserCfczOrderEntity lastOrder = new UserCfczOrderEntity();
    	List<UserCfczOrderEntity> list = userCfczOrderService.queryList(query);
    	if (ValidateUtil.isValid(list)) {
    		lastOrder = list.get(0);
    	}
    	return R.ok().put("lastNum", lastOrder.getCardNum());
    }
    /**
     * 查询餐卡余额
     * @param req
     * @return
     */
    @RequestMapping("/findRemain")
    public R findRemain(HttpServletRequest req){
    	String biz = req.getParameter("biz");
    	//查询列表数据
    	JSONObject params = JSONObject.parseObject(biz);
    	//添加uid到params
    	if (!ValidateUtil.isValid(params.getString("cardNum"))) {
    		Object uid = req.getAttribute(TokenCheckInterceptor.LOGIN_USER_KEY);
    		if (ValidateUtil.isValid(uid)) {
    			params.put("uid", uid);
    		}else{
    			//return R.error(1,"未登录");
    		}
		}
    	UserCfEntity cfEntity = userCfService.queryByCondition(params);
    	return R.ok().put("cfEntity", cfEntity);
    }
}
