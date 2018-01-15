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

import com.lcworld.entity.HysfwRoomEquipmentEntity;
import com.lcworld.service.HysfwRoomEquipmentService;
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
@RequestMapping("hysfwroomequipment")
public class HysfwRoomEquipmentController {
	@Autowired
	private HysfwRoomEquipmentService hysfwRoomEquipmentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("hysfwroomequipment:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<HysfwRoomEquipmentEntity> hysfwRoomEquipmentList = hysfwRoomEquipmentService.queryList(query);
		int total = hysfwRoomEquipmentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(hysfwRoomEquipmentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("hysfwroomequipment:info")
	public R info(@PathVariable("id") Integer id){
		HysfwRoomEquipmentEntity hysfwRoomEquipment = hysfwRoomEquipmentService.queryObject(id);
		
		return R.ok().put("hysfwRoomEquipment", hysfwRoomEquipment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("hysfwroomequipment:save")
	public R save(@RequestBody HysfwRoomEquipmentEntity hysfwRoomEquipment){
		hysfwRoomEquipmentService.save(hysfwRoomEquipment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("hysfwroomequipment:update")
	public R update(@RequestBody HysfwRoomEquipmentEntity hysfwRoomEquipment){
		hysfwRoomEquipmentService.update(hysfwRoomEquipment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("hysfwroomequipment:delete")
	public R delete(@RequestBody Integer[] ids){
		hysfwRoomEquipmentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
