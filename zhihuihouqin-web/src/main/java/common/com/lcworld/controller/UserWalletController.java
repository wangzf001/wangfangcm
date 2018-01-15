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

import com.lcworld.entity.UserWalletEntity;
import com.lcworld.service.UserWalletService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 用户钱包
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:38
 */
@RestController
@RequestMapping("userwallet")
public class UserWalletController {
	@Autowired
	private UserWalletService userWalletService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("userwallet:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserWalletEntity> userWalletList = userWalletService.queryList(query);
		int total = userWalletService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userWalletList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("userwallet:info")
	public R info(@PathVariable("id") Integer id){
		UserWalletEntity userWallet = userWalletService.queryObject(id);
		
		return R.ok().put("userWallet", userWallet);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("userwallet:save")
	public R save(@RequestBody UserWalletEntity userWallet){
		userWalletService.save(userWallet);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("userwallet:update")
	public R update(@RequestBody UserWalletEntity userWallet){
		userWalletService.update(userWallet);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("userwallet:delete")
	public R delete(@RequestBody Integer[] ids){
		userWalletService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
