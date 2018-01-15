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

import com.lcworld.entity.UserWalleorderEntity;
import com.lcworld.service.UserWalleorderService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 用户钱包充值订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:38
 */
@RestController
@RequestMapping("tuserwalletorder")
public class TUserWalletorderController {
	@Autowired
	private UserWalleorderService userWalleorderService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tuserwalletorder:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserWalleorderEntity> userWalleorderList = userWalleorderService.queryList(query);
		int total = userWalleorderService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userWalleorderList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tuserwalletorder:info")
	public R info(@PathVariable("id") Integer id){
		UserWalleorderEntity userWalleorder = userWalleorderService.queryObject(id);
		
		return R.ok().put("userWalleorder", userWalleorder);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tuserwalletorder:save")
	public R save(@RequestBody UserWalleorderEntity userWalleorder){
		userWalleorderService.save(userWalleorder);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tuserwalletorder:update")
	public R update(@RequestBody UserWalleorderEntity userWalleorder){
		userWalleorderService.update(userWalleorder);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("userwalleorder:delete")
	public R delete(@RequestBody Integer[] ids){
		userWalleorderService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
