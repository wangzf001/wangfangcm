package com.lcworld.service;

import com.lcworld.entity.PositionPurchaselimitEntity;

import java.util.List;
import java.util.Map;

/**
 * 职位额度表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-25 14:35:19
 */
public interface PositionPurchaselimitService {
	
	PositionPurchaselimitEntity queryObject(Integer id);
	
	List<PositionPurchaselimitEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PositionPurchaselimitEntity positionPurchaselimit);
	
	void update(PositionPurchaselimitEntity positionPurchaselimit);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
