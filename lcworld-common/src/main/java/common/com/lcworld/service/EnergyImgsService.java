package com.lcworld.service;

import com.lcworld.entity.EnergyImgsEntity;

import java.util.List;
import java.util.Map;

/**
 * 节能减排轮播图
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-15 15:51:55
 */
public interface EnergyImgsService {
	
	EnergyImgsEntity queryObject(Integer id);
	
	List<EnergyImgsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(EnergyImgsEntity energyImgs);
	
	void update(EnergyImgsEntity energyImgs);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
