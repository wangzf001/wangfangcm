package com.lcworld.service;

import com.lcworld.entity.YlfwYyghPeriodEntity;

import java.util.List;
import java.util.Map;

/**
 * 医疗服务预约挂号-时间段表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:51
 */
public interface YlfwYyghPeriodService {
	
	YlfwYyghPeriodEntity queryObject(Integer id);
	
	List<YlfwYyghPeriodEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwYyghPeriodEntity ylfwYyghPeriod);
	
	void update(YlfwYyghPeriodEntity ylfwYyghPeriod);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
