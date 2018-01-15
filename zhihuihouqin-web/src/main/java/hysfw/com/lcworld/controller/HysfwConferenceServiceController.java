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

import com.lcworld.entity.HysfwConferenceServiceEntity;
import com.lcworld.service.HysfwConferenceServiceService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-30 18:43:48
 */
@RestController
@RequestMapping("hysfwconferenceservice")
public class HysfwConferenceServiceController {
	@Autowired
	private HysfwConferenceServiceService hysfwConferenceServiceService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<HysfwConferenceServiceEntity> hysfwConferenceServiceList = hysfwConferenceServiceService.queryList(query);
		int total = hysfwConferenceServiceService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(hysfwConferenceServiceList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	/**
	 * 列表
	 */
	@RequestMapping("/getList")
	public R getList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<HysfwConferenceServiceEntity> list = hysfwConferenceServiceService.queryList(params);
		return R.ok().put("list", list);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("hysfwconferenceservice:info")
	public R info(@PathVariable("id") Integer id){
		HysfwConferenceServiceEntity hysfwConferenceService = hysfwConferenceServiceService.queryObject(id);
		
		return R.ok().put("hysfwConferenceService", hysfwConferenceService);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("hysfwconferenceservice:save")
	public R save(@RequestBody HysfwConferenceServiceEntity hysfwConferenceService){
		hysfwConferenceServiceService.save(hysfwConferenceService);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("hysfwconferenceservice:update")
	public R update(@RequestBody HysfwConferenceServiceEntity hysfwConferenceService){
		hysfwConferenceServiceService.update(hysfwConferenceService);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("hysfwconferenceservice:delete")
	public R delete(@RequestBody Integer[] ids){
		hysfwConferenceServiceService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
