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

import com.lcworld.entity.LffwServiceitemEntity;
import com.lcworld.service.LffwServiceitemService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 理发服务-服务项目
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:13
 */
@RestController
@RequestMapping("lffwserviceitem")
public class LffwServiceitemController {
	@Autowired
	private LffwServiceitemService lffwServiceitemService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("lffwserviceitem:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwServiceitemEntity> lffwServiceitemList = lffwServiceitemService.queryList(query);
		int total = lffwServiceitemService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwServiceitemList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/itemlist")
	public R itemlist(@RequestParam Map<String, Object> params){
	    List<LffwServiceitemEntity> lffwServiceitemList = lffwServiceitemService.queryList(params);
	    return R.ok().put("itemlist", lffwServiceitemList);
	}
	

	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("lffwserviceitem:info")
	public R info(@PathVariable("id") Integer id){
		LffwServiceitemEntity lffwServiceitem = lffwServiceitemService.queryObject(id);
		
		return R.ok().put("lffwServiceitem", lffwServiceitem);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("lffwserviceitem:save")
	public R save(@RequestBody LffwServiceitemEntity lffwServiceitem){
		lffwServiceitemService.save(lffwServiceitem);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("lffwserviceitem:update")
	public R update(@RequestBody LffwServiceitemEntity lffwServiceitem){
		lffwServiceitemService.update(lffwServiceitem);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("lffwserviceitem:delete")
	public R delete(@RequestBody Integer[] ids){
		lffwServiceitemService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
