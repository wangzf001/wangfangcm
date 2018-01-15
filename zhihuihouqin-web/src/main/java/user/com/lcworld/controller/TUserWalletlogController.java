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

import com.lcworld.entity.UserWallelogEntity;
import com.lcworld.service.UserWallelogService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 用户钱包记录
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-02 16:12:58
 */
@RestController
@RequestMapping("tuserwalletlog")
public class TUserWalletlogController {
	@Autowired
	private UserWallelogService userWallelogService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tuserwalletlog:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<UserWallelogEntity> userWallelogList = userWallelogService.queryList(query);
		int total = userWallelogService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(userWallelogList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tuserwalletlog:info")
	public R info(@PathVariable("id") Integer id){
		UserWallelogEntity userWallelog = userWallelogService.queryObject(id);
		
		return R.ok().put("userWallelog", userWallelog);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tuserwalletlog:save")
	public R save(@RequestBody UserWallelogEntity userWallelog){
		userWallelogService.save(userWallelog);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tuserwalletlog:update")
	public R update(@RequestBody UserWallelogEntity userWallelog){
		userWallelogService.update(userWallelog);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tuserwalletlog:delete")
	public R delete(@RequestBody Integer[] ids){
		userWallelogService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
