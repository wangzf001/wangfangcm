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

import com.lcworld.entity.DsfwCategoryEntity;
import com.lcworld.service.DsfwCategoryService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 订水服务-分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:18
 */
@RestController
@RequestMapping("dsfwcategory")
public class DsfwCategoryController {
	@Autowired
	private DsfwCategoryService dsfwCategoryService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<DsfwCategoryEntity> dsfwCategoryList = dsfwCategoryService.queryList(query);
		int total = dsfwCategoryService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(dsfwCategoryList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	@RequestMapping("/queryCategoryList")
	public R queryCategoryList(){
		//查询列表数据
		List<DsfwCategoryEntity> cataList = dsfwCategoryService.queryList(new HashMap<String, Object>());
		return R.ok().put("cataList", cataList);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		DsfwCategoryEntity dsfwCategory = dsfwCategoryService.queryObject(id);
		
		return R.ok().put("dsfwCategory", dsfwCategory);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody DsfwCategoryEntity dsfwCategory){
		dsfwCategoryService.save(dsfwCategory);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody DsfwCategoryEntity dsfwCategory){
		dsfwCategoryService.update(dsfwCategory);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		dsfwCategoryService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
