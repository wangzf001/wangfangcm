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

import com.lcworld.entity.BaseFrontrolesEntity;
import com.lcworld.service.BaseFrontrolesService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 角色表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-11 15:56:43
 */
@RestController
@RequestMapping("basefrontroles")
public class BaseFrontrolesController {
	@Autowired
	private BaseFrontrolesService baseFrontrolesService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("basefrontroles:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BaseFrontrolesEntity> baseFrontrolesList = baseFrontrolesService.queryList(query);
		int total = baseFrontrolesService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(baseFrontrolesList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("basefrontroles:info")
	public R info(@PathVariable("id") Integer id){
		BaseFrontrolesEntity baseFrontroles = baseFrontrolesService.queryObject(id);
		
		return R.ok().put("baseFrontroles", baseFrontroles);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("basefrontroles:save")
	public R save(@RequestBody BaseFrontrolesEntity baseFrontroles){
		baseFrontrolesService.save(baseFrontroles);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("basefrontroles:update")
	public R update(@RequestBody BaseFrontrolesEntity baseFrontroles){
		baseFrontrolesService.update(baseFrontroles);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("basefrontroles:delete")
	public R delete(@RequestBody Integer[] ids){
		baseFrontrolesService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
