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

import com.lcworld.entity.TsjyfwBooktypeEntity;
import com.lcworld.service.TsjyfwBooktypeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 图书借阅服务--图书分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
@RestController
@RequestMapping("tsjyfwbooktype")
public class TsjyfwBooktypeController {
	@Autowired
	private TsjyfwBooktypeService tsjyfwBooktypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tsjyfwbooktype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TsjyfwBooktypeEntity> tsjyfwBooktypeList = tsjyfwBooktypeService.queryList(query);
		int total = tsjyfwBooktypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tsjyfwBooktypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/typelist")
	public R typelist(@RequestParam Map<String, Object> params){
	    List<TsjyfwBooktypeEntity> tsjyfwBooktypeList = tsjyfwBooktypeService.queryList(new HashMap<String,Object>());
	    return R.ok().put("catalist", tsjyfwBooktypeList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tsjyfwbooktype:info")
	public R info(@PathVariable("id") Integer id){
		TsjyfwBooktypeEntity tsjyfwBooktype = tsjyfwBooktypeService.queryObject(id);
		
		return R.ok().put("tsjyfwBooktype", tsjyfwBooktype);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tsjyfwbooktype:save")
	public R save(@RequestBody TsjyfwBooktypeEntity tsjyfwBooktype){
		tsjyfwBooktypeService.save(tsjyfwBooktype);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tsjyfwbooktype:update")
	public R update(@RequestBody TsjyfwBooktypeEntity tsjyfwBooktype){
		tsjyfwBooktypeService.update(tsjyfwBooktype);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tsjyfwbooktype:delete")
	public R delete(@RequestBody Integer[] ids){
		tsjyfwBooktypeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
