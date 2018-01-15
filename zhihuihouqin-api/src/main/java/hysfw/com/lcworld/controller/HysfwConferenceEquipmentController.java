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

import com.lcworld.entity.HysfwConferenceEquipmentEntity;
import com.lcworld.service.HysfwConferenceEquipmentService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
@RestController
@RequestMapping("hysfwconferenceequipment")
public class HysfwConferenceEquipmentController {
	@Autowired
	private HysfwConferenceEquipmentService hysfwConferenceEquipmentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("hysfwconferenceequipment:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<HysfwConferenceEquipmentEntity> hysfwConferenceEquipmentList = hysfwConferenceEquipmentService.queryList(query);
		int total = hysfwConferenceEquipmentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(hysfwConferenceEquipmentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("hysfwconferenceequipment:info")
	public R info(@PathVariable("id") Integer id){
		HysfwConferenceEquipmentEntity hysfwConferenceEquipment = hysfwConferenceEquipmentService.queryObject(id);
		
		return R.ok().put("hysfwConferenceEquipment", hysfwConferenceEquipment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("hysfwconferenceequipment:save")
	public R save(@RequestBody HysfwConferenceEquipmentEntity hysfwConferenceEquipment){
		hysfwConferenceEquipmentService.save(hysfwConferenceEquipment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("hysfwconferenceequipment:update")
	public R update(@RequestBody HysfwConferenceEquipmentEntity hysfwConferenceEquipment){
		hysfwConferenceEquipmentService.update(hysfwConferenceEquipment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("hysfwconferenceequipment:delete")
	public R delete(@RequestBody Integer[] ids){
		hysfwConferenceEquipmentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
