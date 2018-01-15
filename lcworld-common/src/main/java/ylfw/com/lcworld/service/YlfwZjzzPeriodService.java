package com.lcworld.service;

import com.lcworld.entity.YlfwZjzzPeriodEntity;

import java.util.List;
import java.util.Map;

/**
 * 医疗服务专家坐诊-时间段表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:41
 */
public interface YlfwZjzzPeriodService {
	
	YlfwZjzzPeriodEntity queryObject(Integer id);
	
	List<YlfwZjzzPeriodEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwZjzzPeriodEntity ylfwZjzzPeriod);
	
	void update(YlfwZjzzPeriodEntity ylfwZjzzPeriod);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
