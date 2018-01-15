package com.lcworld.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.BuildingEntity;
import com.lcworld.entity.BuildingTypeEntity;
import com.lcworld.service.BuildingService;
import com.lcworld.service.BuildingTypeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 楼栋表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-06 19:19:30
 */
@RestController
@RequestMapping("building")
public class BuildingController {
	@Autowired
	private BuildingService buildingService;
	@Autowired
	private BuildingTypeService buildingTypeService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("building:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BuildingEntity> buildingList = buildingService.queryList(query);
		int total = buildingService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(buildingList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("building:info")
	public R info(@PathVariable("id") Integer id){
		BuildingEntity building = buildingService.queryObject(id);
		Map m = new HashMap<>();
		List<BuildingTypeEntity> typeList = buildingTypeService.queryList(m);
		return R.ok().put("building", building).put("types", typeList);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("building:save")
	public R save(@RequestBody BuildingEntity building){
		buildingService.save(building);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("building:update")
	public R update(@RequestBody BuildingEntity building){
		buildingService.update(building);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("building:delete")
	public R delete(@RequestBody Integer[] ids){
		buildingService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
