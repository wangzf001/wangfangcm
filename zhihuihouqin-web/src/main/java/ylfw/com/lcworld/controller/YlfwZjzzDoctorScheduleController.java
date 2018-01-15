package com.lcworld.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lcworld.dto.YlfwZjzzScheduleEntityDTO;
import com.lcworld.entity.YlfwZjzzDoctorScheduleEntity;
import com.lcworld.service.YlfwZjzzDoctorScheduleService;
import com.lcworld.utils.PageUtils;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.utils.ValidateUtil;


/**
 * 医疗服务专家坐诊—医生计划
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
@RestController
@RequestMapping("ylfwzjzzdoctorschedule")
public class YlfwZjzzDoctorScheduleController {
	@Autowired
	private YlfwZjzzDoctorScheduleService ylfwZjzzDoctorScheduleService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<YlfwZjzzDoctorScheduleEntity> ylfwZjzzDoctorScheduleList = ylfwZjzzDoctorScheduleService.queryList(query);
		int total = ylfwZjzzDoctorScheduleService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(ylfwZjzzDoctorScheduleList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/schedulelist")
	public R schedulelist(@RequestParam Map<String, Object> params){
	    //查询列表数据
	    if(!ValidateUtil.isValid(String.valueOf(params.get("doctorid")))){
	        return R.ok();
	    }
	    Query query = new Query(params);
	    
	    List<YlfwZjzzScheduleEntityDTO> ylfwZjzzDoctorScheduleList = ylfwZjzzDoctorScheduleService.queryscheduleList(query);
	    int total = ylfwZjzzDoctorScheduleService.queryscheduleTotal(query);
	    
	    PageUtils pageUtil = new PageUtils(ylfwZjzzDoctorScheduleList, total, query.getLimit(), query.getPage());
	    
	    return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		YlfwZjzzDoctorScheduleEntity ylfwZjzzDoctorSchedule = ylfwZjzzDoctorScheduleService.queryObject(id);
		
		return R.ok().put("ylfwZjzzDoctorSchedule", ylfwZjzzDoctorSchedule);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody YlfwZjzzDoctorScheduleEntity ylfwZjzzDoctorSchedule){
		try {
            ylfwZjzzDoctorScheduleService.saveSchedule(ylfwZjzzDoctorSchedule);
        } catch (ParseException e) {
           return R.error("保存失败");
        }
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody YlfwZjzzDoctorScheduleEntity ylfwZjzzDoctorSchedule){
		ylfwZjzzDoctorScheduleService.update(ylfwZjzzDoctorSchedule);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		ylfwZjzzDoctorScheduleService.deleteBatch(ids);
		return R.ok();
	}
	
}
