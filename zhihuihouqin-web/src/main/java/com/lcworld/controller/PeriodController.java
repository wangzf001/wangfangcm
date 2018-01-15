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

import com.lcworld.entity.PeriodEntity;
import com.lcworld.entity.YlfwZjzzPeriodEntity;
import com.lcworld.service.PeriodService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 时间段表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-27 10:17:05
 */
@RestController
@RequestMapping("period")
public class PeriodController {
	@Autowired
	private PeriodService periodService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("period:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<PeriodEntity> periodList = periodService.queryList(query);
		int total = periodService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(periodList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
     * 列表
     */
    @RequestMapping("/periodlist")
    public R periodlist(@RequestParam Map<String, Object> params){
        List<PeriodEntity> ylfwZjzzPeriodList = periodService.queryList(new HashMap<String,Object>());
        return R.ok().put("periodlist", ylfwZjzzPeriodList);
    }
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("period:info")
	public R info(@PathVariable("id") Integer id){
		PeriodEntity period = periodService.queryObject(id);
		
		return R.ok().put("period", period);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("period:save")
	public R save(@RequestBody PeriodEntity period){
		periodService.save(period);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("period:update")
	public R update(@RequestBody PeriodEntity period){
		periodService.update(period);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("period:delete")
	public R delete(@RequestBody Integer[] ids){
		periodService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
