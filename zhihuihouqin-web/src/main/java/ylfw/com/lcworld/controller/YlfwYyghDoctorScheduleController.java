package com.lcworld.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.entity.YlfwYyghDoctorScheduleEntity;
import com.lcworld.service.YlfwYyghDoctorScheduleService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;


/**
 * 医疗服务预约挂号——医生计划
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:51
 */
@RestController
@RequestMapping("ylfwyyghdoctorschedule")
public class YlfwYyghDoctorScheduleController {
	@Autowired
	private YlfwYyghDoctorScheduleService ylfwYyghDoctorScheduleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("ylfwyyghdoctorschedule:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwYyghDoctorScheduleEntity> ylfwYyghDoctorScheduleList = ylfwYyghDoctorScheduleService.queryList(query);
		int total = ylfwYyghDoctorScheduleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwYyghDoctorScheduleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("ylfwyyghdoctorschedule:info")
	public R info(@PathVariable("id") Integer id){
		YlfwYyghDoctorScheduleEntity ylfwYyghDoctorSchedule = ylfwYyghDoctorScheduleService.queryObject(id);
		
		return R.ok().put("ylfwYyghDoctorSchedule", ylfwYyghDoctorSchedule);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("ylfwyyghdoctorschedule:save")
	public R save(@RequestBody YlfwYyghDoctorScheduleEntity ylfwYyghDoctorSchedule){
	    ylfwYyghDoctorSchedule.setCreatetime(new Date());
	    ylfwYyghDoctorSchedule.setStatus(0);
	    ylfwYyghDoctorScheduleService.save(ylfwYyghDoctorSchedule);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("ylfwyyghdoctorschedule:update")
	public R update(@RequestBody YlfwYyghDoctorScheduleEntity ylfwYyghDoctorSchedule){
		ylfwYyghDoctorScheduleService.update(ylfwYyghDoctorSchedule);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("ylfwyyghdoctorschedule:delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwYyghDoctorScheduleService.deleteBatch(ids);
		
		return R.ok();
	}
	
}
