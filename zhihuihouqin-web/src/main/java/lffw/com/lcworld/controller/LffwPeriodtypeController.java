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

import com.lcworld.entity.LffwPeriodtypeEntity;
import com.lcworld.service.LffwPeriodtypeService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 理发服务--时间段类型
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 19:30:54
 */
@RestController
@RequestMapping("lffwperiodtype")
public class LffwPeriodtypeController {
	@Autowired
	private LffwPeriodtypeService lffwPeriodtypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LffwPeriodtypeEntity> lffwPeriodtypeList = lffwPeriodtypeService.queryList(query);
		int total = lffwPeriodtypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(lffwPeriodtypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	
	
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		LffwPeriodtypeEntity lffwPeriodtype = lffwPeriodtypeService.queryObject(id);
		
		return R.ok().put("lffwPeriodtype", lffwPeriodtype);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody LffwPeriodtypeEntity lffwPeriodtype){
		lffwPeriodtypeService.save(lffwPeriodtype);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody LffwPeriodtypeEntity lffwPeriodtype){
		lffwPeriodtypeService.update(lffwPeriodtype);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		lffwPeriodtypeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
