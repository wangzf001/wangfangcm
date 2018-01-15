package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.YlfwZjzzPeriodDao;
import com.lcworld.entity.YlfwZjzzPeriodEntity;
import com.lcworld.service.YlfwZjzzPeriodService;



@Service("ylfwZjzzPeriodService")
public class YlfwZjzzPeriodServiceImpl implements YlfwZjzzPeriodService {
	@Autowired
	private YlfwZjzzPeriodDao ylfwZjzzPeriodDao;
	
	@Override
	public YlfwZjzzPeriodEntity queryObject(Integer id){
		return ylfwZjzzPeriodDao.queryObject(id);
	}
	
	@Override
	public List<YlfwZjzzPeriodEntity> queryList(Map<String, Object> map){
		return ylfwZjzzPeriodDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwZjzzPeriodDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwZjzzPeriodEntity ylfwZjzzPeriod){
		ylfwZjzzPeriodDao.save(ylfwZjzzPeriod);
	}
	
	@Override
	public void update(YlfwZjzzPeriodEntity ylfwZjzzPeriod){
		ylfwZjzzPeriodDao.update(ylfwZjzzPeriod);
	}
	
	@Override
	public void delete(Integer id){
		ylfwZjzzPeriodDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwZjzzPeriodDao.deleteBatch(ids);
	}
	
}
