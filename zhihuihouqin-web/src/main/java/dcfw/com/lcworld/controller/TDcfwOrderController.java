package com.lcworld.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.entity.TDcfwOrderEntity;
import com.lcworld.service.TDcfwOrderService;
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
@RequestMapping("dcfworder")
public class TDcfwOrderController {
	@Autowired
	private TDcfwOrderService tDcfwOrderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		Integer packfood = MapUtils.getInteger(params, "packfood");
		if ("".equals((String)params.get("orderStatus"))) {
			params.remove("orderStatus");
		}
		if ("".equals((String)params.get("payStatus"))) {
			params.remove("payStatus");
		}
		String starttime = String.valueOf(params.get("createTimeStart"));
		if (!ValidateUtil.isValid(starttime)) {
			params.remove("createTimeStart");
		}else{
		    params.put("createTimeStart", DateUtils.parse(starttime+" 00:00:00", "yyyy-MM-dd HH:mm:ss"));
		}
		
		String endtime  = String.valueOf(params.get("createTimeEnd"));
		if (!ValidateUtil.isValid(endtime)) {
			params.remove("createTimeEnd");
		}else{
            params.put("createTimeEnd", DateUtils.parse(endtime+" 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		}
		if (packfood!=null&&packfood.intValue()==1) {
		    setpackageParams(params);
		}else{
			if (!ValidateUtil.isValid(params.get("groupbyuid"))) {
				params.put("groupbyuid", 1);
			}
			
		}
		Query query = new Query(params);
		
		List<TDcfwOrderEntity> tDcfwOrderList = tDcfwOrderService.queryList(query);
		int total = tDcfwOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tDcfwOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	private void setpackageParams(Map<String, Object> params) {
	    Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        params.put("createTimeStart",DateUtils.format(c.getTime(),"yyyy-MM-dd HH:mm:ss"));
        c.set(Calendar.HOUR_OF_DAY, 23);
        params.put("createTimeEnd",DateUtils.format(c.getTime(),"yyyy-MM-dd HH:mm:ss"));
        params.put("orderStatus", 1);
        params.put("payStatus", 1);
    }


    /**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		TDcfwOrderEntity tDcfwOrder = tDcfwOrderService.queryObject(id);
		
		return R.ok().put("dcfwOrder", tDcfwOrder);
	}
	
	
	/**
	 *完成
	 */
	@RequestMapping("/finish")
	public R finish(Integer uid){
	    JSONObject params = new JSONObject();
	    params.put("uid", uid);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 1);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        params.put("createTimeStart",DateUtils.format(c.getTime(),"yyyy-MM-dd HH:mm:ss"));
        c.set(Calendar.HOUR_OF_DAY, 23);
        params.put("createTimeEnd",DateUtils.format(c.getTime(),"yyyy-MM-dd HH:mm:ss"));
        tDcfwOrderService.finishOrders(params);
        return R.ok();
	    
	}
	
	
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TDcfwOrderEntity tDcfwOrder){
		tDcfwOrderService.save(tDcfwOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TDcfwOrderEntity tDcfwOrder){
		tDcfwOrderService.update(tDcfwOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		tDcfwOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	/**
	 * 完成
	 */
	@RequestMapping("/updateBatch")
	public R updateBatch(@RequestBody String biz){
		JSONObject params = JSONObject.parseObject(biz);
//		System.out.println(biz);
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.HOUR_OF_DAY, 1);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		params.put("createTimeStart",DateUtils.format(c.getTime(),"yyyy-MM-dd HH:mm:ss"));
		c.set(Calendar.HOUR_OF_DAY, 23);
		params.put("createTimeEnd",DateUtils.format(c.getTime(),"yyyy-MM-dd HH:mm:ss"));
		tDcfwOrderService.finishOrders(params);
		return R.ok();
	}
	
}
