package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.ServiceTypeDao;
import com.lcworld.entity.ServiceTypeEntity;
import com.lcworld.service.ServiceTypeService;



@Service("serviceTypeService")
public class ServiceTypeServiceImpl implements ServiceTypeService {
	@Autowired
	private ServiceTypeDao serviceTypeDao;
	
	@Override
	public ServiceTypeEntity queryObject(Integer id){
		return serviceTypeDao.queryObject(id);
	}
	
	@Override
	public List<ServiceTypeEntity> queryList(Map<String, Object> map){
		return serviceTypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return serviceTypeDao.queryTotal(map);
	}
	
	@Override
	public void save(ServiceTypeEntity serviceType){
		serviceTypeDao.save(serviceType);
	}
	
	@Override
	public void update(ServiceTypeEntity serviceType){
		serviceTypeDao.update(serviceType);
	}
	
	@Override
	public void delete(Integer id){
		serviceTypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		serviceTypeDao.deleteBatch(ids);
	}
	
}
