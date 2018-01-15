package com.lcworld.service;

import com.lcworld.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

/**
 * 楼栋表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-06 19:19:30
 */
public interface BuildingService {
	
	BuildingEntity queryObject(Integer id);
	
	List<BuildingEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BuildingEntity building);
	
	void update(BuildingEntity building);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
