package com.lcworld.service;

import com.lcworld.entity.PeriodEntity;

import java.util.List;
import java.util.Map;

/**
 * 时间段表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-27 10:17:05
 */
public interface PeriodService {
	
	PeriodEntity queryObject(Integer id);
	
	List<PeriodEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PeriodEntity period);
	
	void update(PeriodEntity period);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
