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

import com.lcworld.entity.BgypfwCategoryEntity;
import com.lcworld.service.BgypfwCategoryService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
@RestController
@RequestMapping("bgypfwcategory")
public class BgypfwCategoryController {
	@Autowired
	private BgypfwCategoryService bgypfwCategoryService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
		public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BgypfwCategoryEntity> bgypfwCategoryList = bgypfwCategoryService.queryList(query);
		int total = bgypfwCategoryService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypfwCategoryList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 查询不同级别的分类列表
	 */
	@RequestMapping("/queryCategoryList/{level}")
	public R queryCategoryList(@PathVariable("level") Integer level){
		HashMap<String,Object> map = new HashMap<>();
		map.put("cgrade", level);
		List<BgypfwCategoryEntity> list = bgypfwCategoryService.queryList(map);
		return R.ok().put("categoryList", list);
	}
	/**
	 * 查询不同级别的分类列表
	 */
	@RequestMapping("/queryCategoryListByPid/{pid}")
	public R queryCategoryListByPid(@PathVariable("pid") Integer pid){
		HashMap<String,Object> map = new HashMap<>();
		map.put("pid", pid);
		List<BgypfwCategoryEntity> list = bgypfwCategoryService.queryList(map);
		return R.ok().put("categoryList", list);
	}
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
		public R info(@PathVariable("id") Integer id){
		BgypfwCategoryEntity bgypfwCategory = bgypfwCategoryService.queryObject(id);
		
		return R.ok().put("bgypfwCategory", bgypfwCategory);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
		public R save(@RequestBody BgypfwCategoryEntity bgypfwCategory){
		bgypfwCategoryService.save(bgypfwCategory);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
		public R update(@RequestBody BgypfwCategoryEntity bgypfwCategory){
		bgypfwCategoryService.update(bgypfwCategory);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
		public R delete(@RequestBody Integer[] ids){
		bgypfwCategoryService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
