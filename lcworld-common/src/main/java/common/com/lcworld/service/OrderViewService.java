package com.lcworld.service;

import com.lcworld.entity.OrderViewEntity;

import java.util.List;
import java.util.Map;

/**
 * VIEW
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-11 10:49:38
 */
public interface OrderViewService {
	
	OrderViewEntity queryObject(String id);
	
	List<OrderViewEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderViewEntity orderView);
	
	void update(OrderViewEntity orderView);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
}
