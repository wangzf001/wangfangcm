package com.lcworld.service;

import com.lcworld.entity.BuildingTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 楼栋建筑分类表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-07 10:53:13
 */
public interface BuildingTypeService {
	
	BuildingTypeEntity queryObject(Integer typeId);
	
	List<BuildingTypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BuildingTypeEntity buildingType);
	
	void update(BuildingTypeEntity buildingType);
	
	void delete(Integer typeId);
	
	void deleteBatch(Integer[] typeIds);
}
