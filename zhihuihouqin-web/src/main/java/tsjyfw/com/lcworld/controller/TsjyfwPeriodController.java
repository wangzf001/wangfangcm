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

import com.lcworld.entity.TsjyfwPeriodEntity;
import com.lcworld.service.TsjyfwPeriodService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 图书借阅服务-时间段
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
@RestController
@RequestMapping("tsjyfwperiod")
public class TsjyfwPeriodController {
	@Autowired
	private TsjyfwPeriodService tsjyfwPeriodService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tsjyfwperiod:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TsjyfwPeriodEntity> tsjyfwPeriodList = tsjyfwPeriodService.queryList(query);
		int total = tsjyfwPeriodService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tsjyfwPeriodList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("tsjyfwperiod:info")
	public R info(@PathVariable("id") Integer id){
		TsjyfwPeriodEntity tsjyfwPeriod = tsjyfwPeriodService.queryObject(id);
		
		return R.ok().put("tsjyfwPeriod", tsjyfwPeriod);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tsjyfwperiod:save")
	public R save(@RequestBody TsjyfwPeriodEntity tsjyfwPeriod){
		tsjyfwPeriodService.save(tsjyfwPeriod);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tsjyfwperiod:update")
	public R update(@RequestBody TsjyfwPeriodEntity tsjyfwPeriod){
		tsjyfwPeriodService.update(tsjyfwPeriod);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tsjyfwperiod:delete")
	public R delete(@RequestBody Integer[] ids){
		tsjyfwPeriodService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
