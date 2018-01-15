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

import com.lcworld.entity.LffwPeriodEntity;
import com.lcworld.service.LffwPeriodService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 理发服务- 时间段表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
@RestController
@RequestMapping("lffwperiod")
public class LffwPeriodController {
	@Autowired
	private LffwPeriodService lffwPeriodService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwPeriodEntity> lffwPeriodList = lffwPeriodService.queryList(query);
		int total = lffwPeriodService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwPeriodList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/periodlist")
	public R periodlist(@RequestParam Map<String, Object> params){
	    List<LffwPeriodEntity> lffwPeriodList = lffwPeriodService.queryList(new HashMap<String,Object>());
	    return R.ok().put("periodlist", lffwPeriodList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		LffwPeriodEntity lffwPeriod = lffwPeriodService.queryObject(id);
		
		return R.ok().put("lffwPeriod", lffwPeriod);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody LffwPeriodEntity lffwPeriod){
		lffwPeriodService.save(lffwPeriod);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody LffwPeriodEntity lffwPeriod){
		lffwPeriodService.update(lffwPeriod);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		lffwPeriodService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
