package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TsjyfwPeriodEntity;

import java.util.List;
import java.util.Map;

/**
 * 图书借阅服务-时间段
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
public interface TsjyfwPeriodService {
	
	TsjyfwPeriodEntity queryObject(Integer id);
	
	List<TsjyfwPeriodEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TsjyfwPeriodEntity tsjyfwPeriod);
	
	void update(TsjyfwPeriodEntity tsjyfwPeriod);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<TsjyfwPeriodEntity> querytimeList(JSONObject map);
}
