package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BuildingTypeDao;
import com.lcworld.entity.BuildingTypeEntity;
import com.lcworld.service.BuildingTypeService;



@Service("buildingTypeService")
public class BuildingTypeServiceImpl implements BuildingTypeService {
	@Autowired
	private BuildingTypeDao buildingTypeDao;
	
	@Override
	public BuildingTypeEntity queryObject(Integer typeId){
		return buildingTypeDao.queryObject(typeId);
	}
	
	@Override
	public List<BuildingTypeEntity> queryList(Map<String, Object> map){
		return buildingTypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return buildingTypeDao.queryTotal(map);
	}
	
	@Override
	public void save(BuildingTypeEntity buildingType){
		buildingTypeDao.save(buildingType);
	}
	
	@Override
	public void update(BuildingTypeEntity buildingType){
		buildingTypeDao.update(buildingType);
	}
	
	@Override
	public void delete(Integer typeId){
		buildingTypeDao.delete(typeId);
	}
	
	@Override
	public void deleteBatch(Integer[] typeIds){
		buildingTypeDao.deleteBatch(typeIds);
	}
	
}
