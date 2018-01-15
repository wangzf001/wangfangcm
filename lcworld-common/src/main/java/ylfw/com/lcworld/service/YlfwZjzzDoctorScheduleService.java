package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.DoctorScheduleDTO;
import com.lcworld.dto.ExpertScheduleDTO;
import com.lcworld.dto.YlfwZjzzScheduleEntityDTO;
import com.lcworld.entity.YlfwZjzzDoctorScheduleEntity;
import com.lcworld.utils.Query;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 医疗服务专家坐诊—医生计划
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
public interface YlfwZjzzDoctorScheduleService {
	
	YlfwZjzzDoctorScheduleEntity queryObject(Integer id);
	
	List<YlfwZjzzDoctorScheduleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwZjzzDoctorScheduleEntity ylfwZjzzDoctorSchedule);
	
	void update(YlfwZjzzDoctorScheduleEntity ylfwZjzzDoctorSchedule);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<ExpertScheduleDTO>  queryDocSchedules(JSONObject params);

    List<YlfwZjzzScheduleEntityDTO> queryscheduleList(Query query);

    int queryscheduleTotal(Query query);

    void saveSchedule(YlfwZjzzDoctorScheduleEntity ylfwZjzzDoctorSchedule) throws ParseException;
}
