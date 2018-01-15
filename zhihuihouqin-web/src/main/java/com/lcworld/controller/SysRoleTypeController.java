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

import com.lcworld.entity.SysRoleTypeEntity;
import com.lcworld.service.SysRoleTypeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-13 19:08:42
 */
@RestController
@RequestMapping("sysroletype")
public class SysRoleTypeController {
	@Autowired
	private SysRoleTypeService sysRoleTypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sysroletype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SysRoleTypeEntity> sysRoleTypeList = sysRoleTypeService.queryList(query);
		int total = sysRoleTypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(sysRoleTypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sysroletype:info")
	public R info(@PathVariable("id") Integer id){
		SysRoleTypeEntity sysRoleType = sysRoleTypeService.queryObject(id);
		
		return R.ok().put("sysRoleType", sysRoleType);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sysroletype:save")
	public R save(@RequestBody SysRoleTypeEntity sysRoleType){
		sysRoleTypeService.save(sysRoleType);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sysroletype:update")
	public R update(@RequestBody SysRoleTypeEntity sysRoleType){
		sysRoleTypeService.update(sysRoleType);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sysroletype:delete")
	public R delete(@RequestBody Integer[] ids){
		sysRoleTypeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
