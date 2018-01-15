package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.EnergyImgsDao;
import com.lcworld.entity.EnergyImgsEntity;
import com.lcworld.service.EnergyImgsService;



@Service("energyImgsService")
public class EnergyImgsServiceImpl implements EnergyImgsService {
	@Autowired
	private EnergyImgsDao energyImgsDao;
	
	@Override
	public EnergyImgsEntity queryObject(Integer id){
		return energyImgsDao.queryObject(id);
	}
	
	@Override
	public List<EnergyImgsEntity> queryList(Map<String, Object> map){
		return energyImgsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return energyImgsDao.queryTotal(map);
	}
	
	@Override
	public void save(EnergyImgsEntity energyImgs){
		energyImgsDao.save(energyImgs);
	}
	
	@Override
	public void update(EnergyImgsEntity energyImgs){
		energyImgsDao.update(energyImgs);
	}
	
	@Override
	public void delete(Integer id){
		energyImgsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		energyImgsDao.deleteBatch(ids);
	}
	
}
