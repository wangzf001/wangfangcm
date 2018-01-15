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

import com.lcworld.entity.PurchaseAccounlogEntity;
import com.lcworld.service.PurchaseAccounlogService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 采购账号记录
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-31 14:40:06
 */
@RestController
@RequestMapping("purchaseaccounlog")
public class PurchaseAccounlogController {
	@Autowired
	private PurchaseAccounlogService purchaseAccounlogService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("purchaseaccounlog:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PurchaseAccounlogEntity> purchaseAccounlogList = purchaseAccounlogService.queryList(query);
		int total = purchaseAccounlogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(purchaseAccounlogList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("purchaseaccounlog:info")
	public R info(@PathVariable("id") Integer id){
		PurchaseAccounlogEntity purchaseAccounlog = purchaseAccounlogService.queryObject(id);
		
		return R.ok().put("purchaseAccounlog", purchaseAccounlog);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("purchaseaccounlog:save")
	public R save(@RequestBody PurchaseAccounlogEntity purchaseAccounlog){
		purchaseAccounlogService.save(purchaseAccounlog);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("purchaseaccounlog:update")
	public R update(@RequestBody PurchaseAccounlogEntity purchaseAccounlog){
		purchaseAccounlogService.update(purchaseAccounlog);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("purchaseaccounlog:delete")
	public R delete(@RequestBody Integer[] ids){
		purchaseAccounlogService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
