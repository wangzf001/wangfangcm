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

import com.lcworld.entity.EnergyInformationEntity;
import com.lcworld.service.EnergyInformationService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 节能减排资讯信息
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-16 15:59:18
 */
@RestController
@RequestMapping("energyinformation")
public class EnergyInformationController {
	@Autowired
	private EnergyInformationService energyInformationService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("energyinformation:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<EnergyInformationEntity> energyInformationList = energyInformationService.queryList(query);
		int total = energyInformationService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(energyInformationList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("energyinformation:info")
	public R info(@PathVariable("id") Integer id){
		EnergyInformationEntity energyInformation = energyInformationService.queryObject(id);
		
		return R.ok().put("energyInformation", energyInformation);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("energyinformation:save")
	public R save(@RequestBody EnergyInformationEntity energyInformation){
		energyInformation.setCreatTime(DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN));
		energyInformationService.save(energyInformation);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("energyinformation:update")
	public R update(@RequestBody EnergyInformationEntity energyInformation){
		energyInformationService.update(energyInformation);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("energyinformation:delete")
	public R delete(@RequestBody Integer[] ids){
		energyInformationService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
