package com.lcworld.service;

import com.lcworld.entity.DeliveryOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-11 15:13:39
 */
public interface DeliveryOrderService {
	
	DeliveryOrderEntity queryObject(Integer id);
	
	List<DeliveryOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DeliveryOrderEntity deliveryOrder);
	
	void update(DeliveryOrderEntity deliveryOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
