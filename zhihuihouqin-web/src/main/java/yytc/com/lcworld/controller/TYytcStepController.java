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

import com.lcworld.entity.TYytcStepEntity;
import com.lcworld.service.TYytcStepService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
@RestController
@RequestMapping("tyytcstep")
public class TYytcStepController {
	@Autowired
	private TYytcStepService tYytcStepService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TYytcStepEntity> tYytcStepList = tYytcStepService.queryList(query);
		int total = tYytcStepService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tYytcStepList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{sId}")
	public R info(@PathVariable("sId") Integer sId){
		TYytcStepEntity tYytcStep = tYytcStepService.queryObject(sId);
		
		return R.ok().put("tYytcStep", tYytcStep);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody TYytcStepEntity tYytcStep){
		tYytcStepService.save(tYytcStep);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody TYytcStepEntity tYytcStep){
		tYytcStepService.update(tYytcStep);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] sIds){
		tYytcStepService.deleteBatch(sIds);
		
		return R.ok();
	}
	
}
