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

import com.lcworld.entity.TYytcIngredientEntity;
import com.lcworld.service.TYytcIngredientService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
@RestController
@RequestMapping("tyytcingredient")
public class TYytcIngredientController {
	@Autowired
	private TYytcIngredientService tYytcIngredientService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TYytcIngredientEntity> tYytcIngredientList = tYytcIngredientService.queryList(query);
		int total = tYytcIngredientService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tYytcIngredientList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{iId}")
	public R info(@PathVariable("iId") Integer iId){
		TYytcIngredientEntity tYytcIngredient = tYytcIngredientService.queryObject(iId);
		
		return R.ok().put("tYytcIngredient", tYytcIngredient);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TYytcIngredientEntity tYytcIngredient){
		tYytcIngredientService.save(tYytcIngredient);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TYytcIngredientEntity tYytcIngredient){
		tYytcIngredientService.update(tYytcIngredient);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] iIds){
		tYytcIngredientService.deleteBatch(iIds);
		
		return R.ok();
	}
	
}
