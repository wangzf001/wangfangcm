package com.lcworld.service;

import com.lcworld.entity.LffwPeriodEntity;

import java.util.List;
import java.util.Map;

/**
 * 理发服务- 时间段表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
public interface LffwPeriodService {
	
	LffwPeriodEntity queryObject(Integer id);
	
	List<LffwPeriodEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwPeriodEntity lffwPeriod);
	
	void update(LffwPeriodEntity lffwPeriod);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
