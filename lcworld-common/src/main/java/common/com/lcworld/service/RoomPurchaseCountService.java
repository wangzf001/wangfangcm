package com.lcworld.service;

import com.lcworld.entity.RoomPurchaseCountEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-30 15:30:18
 */
public interface RoomPurchaseCountService {
	
	RoomPurchaseCountEntity queryObject(Integer id);
	
	List<RoomPurchaseCountEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RoomPurchaseCountEntity roomPurchaseCount);
	
	void update(RoomPurchaseCountEntity roomPurchaseCount);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
