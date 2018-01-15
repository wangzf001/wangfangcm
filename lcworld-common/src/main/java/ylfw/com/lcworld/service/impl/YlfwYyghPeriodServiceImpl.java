package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.YlfwYyghPeriodDao;
import com.lcworld.entity.YlfwYyghPeriodEntity;
import com.lcworld.service.YlfwYyghPeriodService;



@Service("ylfwYyghPeriodService")
public class YlfwYyghPeriodServiceImpl implements YlfwYyghPeriodService {
	@Autowired
	private YlfwYyghPeriodDao ylfwYyghPeriodDao;
	
	@Override
	public YlfwYyghPeriodEntity queryObject(Integer id){
		return ylfwYyghPeriodDao.queryObject(id);
	}
	
	@Override
	public List<YlfwYyghPeriodEntity> queryList(Map<String, Object> map){
		return ylfwYyghPeriodDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwYyghPeriodDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwYyghPeriodEntity ylfwYyghPeriod){
		ylfwYyghPeriodDao.save(ylfwYyghPeriod);
	}
	
	@Override
	public void update(YlfwYyghPeriodEntity ylfwYyghPeriod){
		ylfwYyghPeriodDao.update(ylfwYyghPeriod);
	}
	
	@Override
	public void delete(Integer id){
		ylfwYyghPeriodDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwYyghPeriodDao.deleteBatch(ids);
	}
	
}
