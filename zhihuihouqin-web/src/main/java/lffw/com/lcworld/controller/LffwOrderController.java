package com.lcworld.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.service.LffwOrderService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 理发服务订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
@RestController
@RequestMapping("lffworder")
public class LffwOrderController {
	@Autowired
	private LffwOrderService lffwOrderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("lffworder:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwOrderEntity> lffwOrderList = lffwOrderService.queryList(query);
		int total = lffwOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/orderlist")
	public R orderlist(@RequestParam Map<String, Object> params){
	    //查询列表数据
	    Query query = new Query(params);
	    
	    List<Map<String,Object>> lffwOrderList = lffwOrderService.queryorderList(query);
	    int total = lffwOrderService.queryorderTotal(query);
	    
	    PageUtils pageUtil = new PageUtils(lffwOrderList, total, query.getLimit(), query.getPage());
	    
	    return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("lffworder:info")
	public R info(@PathVariable("id") Integer id){
		LffwOrderEntity lffwOrder = lffwOrderService.queryObject(id);
		
		return R.ok().put("lffwOrder", lffwOrder);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/checkDetail")
	public R checkDetail(Integer orderid){
	    LffwOrderEntity order = new LffwOrderEntity();
	    order.setCheckstatus(1);
	    order.setId(orderid);
	    lffwOrderService.update(order);
	    return R.ok();
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/jd")
	public R jd(Integer orderid){
	    LffwOrderEntity order = lffwOrderService.queryObject(orderid);
	    if(1 == order.getPaystatus()){
	        order.setStatus(2);
	        lffwOrderService.update(order);
	    }else{
	        return R.error("只可以接已付款的单");
	    }
	    return R.ok();
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("lffworder:save")
	public R save(@RequestBody LffwOrderEntity lffwOrder){
		lffwOrderService.save(lffwOrder);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("lffworder:update")
	public R update(@RequestBody LffwOrderEntity lffwOrder){
		lffwOrderService.update(lffwOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("lffworder:delete")
	public R delete(@RequestBody Integer[] ids){
		lffwOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
