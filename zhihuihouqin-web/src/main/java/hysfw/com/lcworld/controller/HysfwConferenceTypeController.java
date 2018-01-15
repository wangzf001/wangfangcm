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

import com.lcworld.entity.HysfwConferenceTypeEntity;
import com.lcworld.service.HysfwConferenceTypeService;
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
@RequestMapping("hysfwconferencetype")
public class HysfwConferenceTypeController {
	@Autowired
	private HysfwConferenceTypeService hysfwConferenceTypeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("hysfwconferencetype:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<HysfwConferenceTypeEntity> hysfwConferenceTypeList = hysfwConferenceTypeService.queryList(query);
		int total = hysfwConferenceTypeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(hysfwConferenceTypeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("hysfwconferencetype:info")
	public R info(@PathVariable("id") Integer id){
		HysfwConferenceTypeEntity hysfwConferenceType = hysfwConferenceTypeService.queryObject(id);
		
		return R.ok().put("hysfwConferenceType", hysfwConferenceType);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("hysfwconferencetype:save")
	public R save(@RequestBody HysfwConferenceTypeEntity hysfwConferenceType){
		hysfwConferenceTypeService.save(hysfwConferenceType);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("hysfwconferencetype:update")
	public R update(@RequestBody HysfwConferenceTypeEntity hysfwConferenceType){
		hysfwConferenceTypeService.update(hysfwConferenceType);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("hysfwconferencetype:delete")
	public R delete(@RequestBody Integer[] ids){
		hysfwConferenceTypeService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
