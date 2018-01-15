package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.EnergyInformationDao;
import com.lcworld.entity.EnergyInformationEntity;
import com.lcworld.service.EnergyInformationService;



@Service("energyInformationService")
public class EnergyInformationServiceImpl implements EnergyInformationService {
	@Autowired
	private EnergyInformationDao energyInformationDao;
	
	@Override
	public EnergyInformationEntity queryObject(Integer id){
		return energyInformationDao.queryObject(id);
	}
	
	@Override
	public List<EnergyInformationEntity> queryList(Map<String, Object> map){
		return energyInformationDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return energyInformationDao.queryTotal(map);
	}
	
	@Override
	public void save(EnergyInformationEntity energyInformation){
		energyInformationDao.save(energyInformation);
	}
	
	@Override
	public void update(EnergyInformationEntity energyInformation){
		energyInformationDao.update(energyInformation);
	}
	
	@Override
	public void delete(Integer id){
		energyInformationDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		energyInformationDao.deleteBatch(ids);
	}
	
}
