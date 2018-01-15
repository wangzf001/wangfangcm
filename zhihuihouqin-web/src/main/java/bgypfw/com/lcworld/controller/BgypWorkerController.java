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

import com.lcworld.entity.BgypWorkerEntity;
import com.lcworld.service.BgypWorkerService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-08 16:15:29
 */
@RestController
@RequestMapping("bgypworker")
public class BgypWorkerController {
	@Autowired
	private BgypWorkerService bgypWorkerService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("bgypworker:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BgypWorkerEntity> bgypWorkerList = bgypWorkerService.queryList(query);
		int total = bgypWorkerService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(bgypWorkerList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("bgypworker:info")
	public R info(@PathVariable("id") Integer id){
		BgypWorkerEntity bgypWorker = bgypWorkerService.queryObject(id);
		
		return R.ok().put("bgypWorker", bgypWorker);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("bgypworker:save")
	public R save(@RequestBody BgypWorkerEntity bgypWorker){
		bgypWorkerService.save(bgypWorker);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("bgypworker:update")
	public R update(@RequestBody BgypWorkerEntity bgypWorker){
		bgypWorkerService.update(bgypWorker);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("bgypworker:delete")
	public R delete(@RequestBody Integer[] ids){
		bgypWorkerService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
