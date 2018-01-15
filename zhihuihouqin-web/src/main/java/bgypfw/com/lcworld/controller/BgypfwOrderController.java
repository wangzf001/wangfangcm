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

import com.lcworld.entity.BgypfwOrderEntity;
import com.lcworld.service.BgypfwOrderService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 办公用品服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
@RestController
@RequestMapping("bgypfworder")
public class BgypfwOrderController {
	@Autowired
	private BgypfwOrderService bgypfwOrderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
		public R list(@RequestParam Map<String, Object> params){
		if ("".equals((String)params.get("orderStatus"))) {
			params.remove("orderStatus");
		}
		if ("".equals((String)params.get("payStatus"))) {
			params.remove("payStatus");
		}
		if ("".equals((String)params.get("createTimeStart"))) {
			params.remove("createTimeStart");
		}
		if ("".equals((String)params.get("createTimeEnd"))) {
			params.remove("createTimeEnd");
		}
		params.put("webQuery", 1);
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwOrderEntity> bgypfwOrderList = bgypfwOrderService.queryList(query);
		int total = bgypfwOrderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwOrderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
		public R info(@PathVariable("id") Integer id){
		BgypfwOrderEntity bgypfwOrder = bgypfwOrderService.queryObject(id);
		
		return R.ok().put("bgypfwOrder", bgypfwOrder);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
		public R save(@RequestBody BgypfwOrderEntity bgypfwOrder){
		bgypfwOrderService.save(bgypfwOrder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
		public R update(@RequestBody BgypfwOrderEntity bgypfwOrder){
		bgypfwOrderService.update(bgypfwOrder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
		public R delete(@RequestBody Integer[] ids){
		bgypfwOrderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
