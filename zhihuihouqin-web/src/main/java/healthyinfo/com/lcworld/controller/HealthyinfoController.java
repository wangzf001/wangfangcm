package com.lcworld.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.HealthyinfoEntity;
import com.lcworld.service.HealthyinfoService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 健康资讯
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 15:14:38
 */
@RestController
@RequestMapping("healthyinfo")
public class HealthyinfoController {
	@Autowired
	private HealthyinfoService healthyinfoService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("healthyinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<HealthyinfoEntity> healthyinfoList = healthyinfoService.queryList(query);
		int total = healthyinfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(healthyinfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("healthyinfo:info")
	public R info(@PathVariable("id") Integer id){
		HealthyinfoEntity healthyinfo = healthyinfoService.queryObject(id);
		
		return R.ok().put("healthyinfo", healthyinfo);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("healthyinfo:save")
	public R save(@RequestBody HealthyinfoEntity healthyinfo){
	    healthyinfo.setCreatetime(new Date());
		healthyinfoService.save(healthyinfo);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("healthyinfo:update")
	public R update(@RequestBody HealthyinfoEntity healthyinfo){
		healthyinfoService.update(healthyinfo);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("healthyinfo:delete")
	public R delete(@RequestBody Integer[] ids){
		healthyinfoService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
