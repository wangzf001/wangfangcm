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

import com.lcworld.entity.YlfwZjzzPeriodEntity;
import com.lcworld.service.YlfwZjzzPeriodService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 医疗服务专家坐诊-时间段表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:41
 */
@RestController
@RequestMapping("ylfwzjzzperiod")
public class YlfwZjzzPeriodController {
	@Autowired
	private YlfwZjzzPeriodService ylfwZjzzPeriodService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ylfwzjzzperiod:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwZjzzPeriodEntity> ylfwZjzzPeriodList = ylfwZjzzPeriodService.queryList(query);
		int total = ylfwZjzzPeriodService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwZjzzPeriodList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/periodlist")
	public R periodlist(@RequestParam Map<String, Object> params){
	    List<YlfwZjzzPeriodEntity> ylfwZjzzPeriodList = ylfwZjzzPeriodService.queryList(new HashMap<String,Object>());
	    return R.ok().put("periodlist", ylfwZjzzPeriodList);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ylfwzjzzperiod:info")
	public R info(@PathVariable("id") Integer id){
		YlfwZjzzPeriodEntity ylfwZjzzPeriod = ylfwZjzzPeriodService.queryObject(id);
		
		return R.ok().put("ylfwZjzzPeriod", ylfwZjzzPeriod);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ylfwzjzzperiod:save")
	public R save(@RequestBody YlfwZjzzPeriodEntity ylfwZjzzPeriod){
		ylfwZjzzPeriodService.save(ylfwZjzzPeriod);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ylfwzjzzperiod:update")
	public R update(@RequestBody YlfwZjzzPeriodEntity ylfwZjzzPeriod){
		ylfwZjzzPeriodService.update(ylfwZjzzPeriod);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ylfwzjzzperiod:delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwZjzzPeriodService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
