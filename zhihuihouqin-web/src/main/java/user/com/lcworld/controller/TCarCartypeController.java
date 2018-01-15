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

import com.lcworld.entity.TCarCartypeEntity;
import com.lcworld.service.TCarCartypeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 车辆类型
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-28 17:22:36
 */
@RestController
@RequestMapping("tcarcartype")
public class TCarCartypeController {
	@Autowired
	private TCarCartypeService tCarCartypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tcarcartype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TCarCartypeEntity> tCarCartypeList = tCarCartypeService.queryList(query);
		int total = tCarCartypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tCarCartypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tcarcartype:info")
	public R info(@PathVariable("id") Integer id){
		TCarCartypeEntity tCarCartype = tCarCartypeService.queryObject(id);
		
		return R.ok().put("tCarCartype", tCarCartype);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tcarcartype:save")
	public R save(@RequestBody TCarCartypeEntity tCarCartype){
		tCarCartypeService.save(tCarCartype);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tcarcartype:update")
	public R update(@RequestBody TCarCartypeEntity tCarCartype){
		tCarCartypeService.update(tCarCartype);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tcarcartype:delete")
	public R delete(@RequestBody Integer[] ids){
		tCarCartypeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
