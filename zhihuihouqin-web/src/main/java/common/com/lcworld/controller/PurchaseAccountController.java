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

import com.lcworld.entity.PurchaseAccountEntity;
import com.lcworld.service.PurchaseAccountService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 采购账户
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:37
 */
@RestController
@RequestMapping("purchaseaccount")
public class PurchaseAccountController {
	@Autowired
	private PurchaseAccountService purchaseAccountService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("purchaseaccount:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PurchaseAccountEntity> purchaseAccountList = purchaseAccountService.queryList(query);
		int total = purchaseAccountService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(purchaseAccountList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("purchaseaccount:info")
	public R info(@PathVariable("id") Integer id){
		PurchaseAccountEntity purchaseAccount = purchaseAccountService.queryObject(id);
		
		return R.ok().put("purchaseAccount", purchaseAccount);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("purchaseaccount:save")
	public R save(@RequestBody PurchaseAccountEntity purchaseAccount){
		purchaseAccountService.save(purchaseAccount);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("purchaseaccount:update")
	public R update(@RequestBody PurchaseAccountEntity purchaseAccount){
		purchaseAccountService.update(purchaseAccount);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("purchaseaccount:delete")
	public R delete(@RequestBody Integer[] ids){
		purchaseAccountService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
