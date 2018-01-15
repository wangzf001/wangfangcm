package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.PeriodDao;
import com.lcworld.entity.PeriodEntity;
import com.lcworld.service.PeriodService;



@Service("periodService")
public class PeriodServiceImpl implements PeriodService {
	@Autowired
	private PeriodDao periodDao;
	
	@Override
	public PeriodEntity queryObject(Integer id){
		return periodDao.queryObject(id);
	}
	
	@Override
	public List<PeriodEntity> queryList(Map<String, Object> map){
		return periodDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return periodDao.queryTotal(map);
	}
	
	@Override
	public void save(PeriodEntity period){
		periodDao.save(period);
	}
	
	@Override
	public void update(PeriodEntity period){
		periodDao.update(period);
	}
	
	@Override
	public void delete(Integer id){
		periodDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		periodDao.deleteBatch(ids);
	}
	
}
