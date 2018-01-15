package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.DoctorScheduleDTO;
import com.lcworld.entity.YlfwYyghDoctorScheduleEntity;

import java.util.List;
import java.util.Map;

/**
 * 医疗服务预约挂号——医生计划
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:51
 */
public interface YlfwYyghDoctorScheduleService {
	
	YlfwYyghDoctorScheduleEntity queryObject(Integer id);
	
	List<YlfwYyghDoctorScheduleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwYyghDoctorScheduleEntity ylfwYyghDoctorSchedule);
	
	void update(YlfwYyghDoctorScheduleEntity ylfwYyghDoctorSchedule);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

     List<DoctorScheduleDTO> queryDocSchedules(JSONObject params);
}
