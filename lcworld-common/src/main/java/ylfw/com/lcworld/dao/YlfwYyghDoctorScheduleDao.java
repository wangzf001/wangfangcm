package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.DoctorScheduleDTO;
import com.lcworld.entity.YlfwYyghDoctorScheduleEntity;

/**
 * 医疗服务预约挂号——医生计划
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:51
 */
public interface YlfwYyghDoctorScheduleDao extends BaseDao<YlfwYyghDoctorScheduleEntity> {

    List<DoctorScheduleDTO> queryDocSchedules(JSONObject params);
	
}
