package com.lcworld.service;

import com.lcworld.entity.EnergyCostEntity;

import java.util.List;
import java.util.Map;

/**
 * 能耗表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-13 15:28:32
 */
public interface EnergyCostService {
	
	EnergyCostEntity queryObject(Integer eId);
	
	List<EnergyCostEntity> queryList(Map<String, Object> map);
	
	List<EnergyCostEntity>energyCostQuery(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(EnergyCostEntity energyCost);
	
	void update(EnergyCostEntity energyCost);
	
	void delete(Integer eId);
	
	void deleteBatch(Integer[] eIds);

	void remoteGetEnergyCost();

	EnergyCostEntity energyCostTotalQuery(Map<String, Object> map);
	
	Integer bothCostQuery(Integer energyType);
}
