package com.lcworld.service;

import com.lcworld.entity.YlfwHospitalEntity;

import java.util.List;
import java.util.Map;

/**
 * 医疗服务医院表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-20 13:09:47
 */
public interface YlfwHospitalService {
	
	YlfwHospitalEntity queryObject(Integer id);
	
	List<YlfwHospitalEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwHospitalEntity ylfwHospital);
	
	void update(YlfwHospitalEntity ylfwHospital);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
