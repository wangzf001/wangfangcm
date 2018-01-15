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

import com.lcworld.entity.BuildingTypeEntity;
import com.lcworld.service.BuildingTypeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 楼栋建筑分类表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-07 10:53:13
 */
@RestController
@RequestMapping("buildingtype")
public class BuildingTypeController {
	@Autowired
	private BuildingTypeService buildingTypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("buildingtype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BuildingTypeEntity> buildingTypeList = buildingTypeService.queryList(query);
		int total = buildingTypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(buildingTypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{typeId}")
	@RequiresPermissions("buildingtype:info")
	public R info(@PathVariable("typeId") Integer typeId){
		BuildingTypeEntity buildingType = buildingTypeService.queryObject(typeId);
		
		return R.ok().put("buildingType", buildingType);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("buildingtype:save")
	public R save(@RequestBody BuildingTypeEntity buildingType){
		buildingTypeService.save(buildingType);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("buildingtype:update")
	public R update(@RequestBody BuildingTypeEntity buildingType){
		buildingTypeService.update(buildingType);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("buildingtype:delete")
	public R delete(@RequestBody Integer[] typeIds){
		buildingTypeService.deleteBatch(typeIds);
		
		return R.ok();
	}
	
}
