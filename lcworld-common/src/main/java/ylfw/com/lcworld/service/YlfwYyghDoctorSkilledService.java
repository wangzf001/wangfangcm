package com.lcworld.service;

import com.lcworld.entity.YlfwYyghDoctorSkilledEntity;

import java.util.List;
import java.util.Map;

/**
 * 医疗服务预约挂号医生擅长疾病
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:51
 */
public interface YlfwYyghDoctorSkilledService {
	
	YlfwYyghDoctorSkilledEntity queryObject(Integer id);
	
	List<YlfwYyghDoctorSkilledEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwYyghDoctorSkilledEntity ylfwYyghDoctorSkilled);
	
	void update(YlfwYyghDoctorSkilledEntity ylfwYyghDoctorSkilled);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
