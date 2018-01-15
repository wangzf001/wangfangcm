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

import com.lcworld.entity.TBxwxItemEntity;
import com.lcworld.service.TBxwxItemService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 报修维修项
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
@RestController
@RequestMapping("tbxwxitem")
public class TBxwxItemController {
	@Autowired
	private TBxwxItemService tBxwxItemService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tbxwxitem:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TBxwxItemEntity> tBxwxItemList = tBxwxItemService.queryList(query);
		int total = tBxwxItemService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tBxwxItemList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tbxwxitem:info")
	public R info(@PathVariable("id") Integer id){
		TBxwxItemEntity tBxwxItem = tBxwxItemService.queryObject(id);
		
		return R.ok().put("tBxwxItem", tBxwxItem);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tbxwxitem:save")
	public R save(@RequestBody TBxwxItemEntity tBxwxItem){
		tBxwxItemService.save(tBxwxItem);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tbxwxitem:update")
	public R update(@RequestBody TBxwxItemEntity tBxwxItem){
		tBxwxItemService.update(tBxwxItem);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tbxwxitem:delete")
	public R delete(@RequestBody Integer[] ids){
		tBxwxItemService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
