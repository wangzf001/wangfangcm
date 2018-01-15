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

import com.lcworld.entity.IndexCarouselEntity;
import com.lcworld.service.IndexCarouselService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-13 14:16:27
 */
@RestController
@RequestMapping("indexcarousel")
public class IndexCarouselController {
	@Autowired
	private IndexCarouselService indexCarouselService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("indexcarousel:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<IndexCarouselEntity> indexCarouselList = indexCarouselService.queryList(query);
		int total = indexCarouselService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(indexCarouselList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("indexcarousel:info")
	public R info(@PathVariable("id") Integer id){
		IndexCarouselEntity indexCarousel = indexCarouselService.queryObject(id);
		
		return R.ok().put("indexCarousel", indexCarousel);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("indexcarousel:save")
	public R save(@RequestBody IndexCarouselEntity indexCarousel){
		indexCarouselService.save(indexCarousel);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("indexcarousel:update")
	public R update(@RequestBody IndexCarouselEntity indexCarousel){
		indexCarouselService.update(indexCarousel);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("indexcarousel:delete")
	public R delete(@RequestBody Integer[] ids){
		indexCarouselService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
