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

import com.lcworld.entity.HysfwAppointmentEntity;
import com.lcworld.service.HysfwAppointmentService;
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
@RequestMapping("hysfwappointment")
public class HysfwAppointmentController {
	@Autowired
	private HysfwAppointmentService hysfwAppointmentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("hysfwappointment:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<HysfwAppointmentEntity> hysfwAppointmentList = hysfwAppointmentService.queryList(query);
		int total = hysfwAppointmentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(hysfwAppointmentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("hysfwappointment:info")
	public R info(@PathVariable("id") Integer id){
		HysfwAppointmentEntity hysfwAppointment = hysfwAppointmentService.queryObject(id);
		
		return R.ok().put("hysfwAppointment", hysfwAppointment);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("hysfwappointment:save")
	public R save(@RequestBody HysfwAppointmentEntity hysfwAppointment){
		hysfwAppointmentService.save(hysfwAppointment);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("hysfwappointment:update")
	public R update(@RequestBody HysfwAppointmentEntity hysfwAppointment){
		hysfwAppointmentService.update(hysfwAppointment);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("hysfwappointment:delete")
	public R delete(@RequestBody Integer[] ids){
		hysfwAppointmentService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
