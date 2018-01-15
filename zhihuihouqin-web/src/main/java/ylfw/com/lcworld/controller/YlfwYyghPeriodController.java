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

import com.lcworld.entity.YlfwYyghPeriodEntity;
import com.lcworld.service.YlfwYyghPeriodService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 医疗服务预约挂号-时间段表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:51
 */
@RestController
@RequestMapping("ylfwyyghperiod")
public class YlfwYyghPeriodController {
	@Autowired
	private YlfwYyghPeriodService ylfwYyghPeriodService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ylfwyyghperiod:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwYyghPeriodEntity> ylfwYyghPeriodList = ylfwYyghPeriodService.queryList(query);
		int total = ylfwYyghPeriodService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwYyghPeriodList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ylfwyyghperiod:info")
	public R info(@PathVariable("id") Integer id){
		YlfwYyghPeriodEntity ylfwYyghPeriod = ylfwYyghPeriodService.queryObject(id);
		
		return R.ok().put("ylfwYyghPeriod", ylfwYyghPeriod);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ylfwyyghperiod:save")
	public R save(@RequestBody YlfwYyghPeriodEntity ylfwYyghPeriod){
		ylfwYyghPeriodService.save(ylfwYyghPeriod);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ylfwyyghperiod:update")
	public R update(@RequestBody YlfwYyghPeriodEntity ylfwYyghPeriod){
		ylfwYyghPeriodService.update(ylfwYyghPeriod);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ylfwyyghperiod:delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwYyghPeriodService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
