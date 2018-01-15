package com.lcworld.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.lcworld.utils.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.EnergyImgsEntity;
import com.lcworld.service.EnergyImgsService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 节能减排轮播图
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-15 15:51:55
 */
@RestController
@RequestMapping("energyimgs")
public class EnergyImgsController {
	@Autowired
	private EnergyImgsService energyImgsService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("energyimgs:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<EnergyImgsEntity> energyImgsList = energyImgsService.queryList(query);
		int total = energyImgsService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(energyImgsList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("energyimgs:info")
	public R info(@PathVariable("id") Integer id){
		EnergyImgsEntity energyImgs = energyImgsService.queryObject(id);
		
		return R.ok().put("energyImgs", energyImgs);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("energyimgs:save")
	public R save(@RequestBody EnergyImgsEntity energyImgs){
		energyImgs.setCreatTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
		energyImgsService.save(energyImgs);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("energyimgs:update")
	public R update(@RequestBody EnergyImgsEntity energyImgs){
		energyImgsService.update(energyImgs);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("energyimgs:delete")
	public R delete(@RequestBody Integer[] ids){
		energyImgsService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
