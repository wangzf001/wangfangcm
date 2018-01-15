package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.LffwPeriodDao;
import com.lcworld.entity.LffwPeriodEntity;
import com.lcworld.service.LffwPeriodService;



@Service("lffwPeriodService")
public class LffwPeriodServiceImpl implements LffwPeriodService {
	@Autowired
	private LffwPeriodDao lffwPeriodDao;
	
	@Override
	public LffwPeriodEntity queryObject(Integer id){
		return lffwPeriodDao.queryObject(id);
	}
	
	@Override
	public List<LffwPeriodEntity> queryList(Map<String, Object> map){
		return lffwPeriodDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwPeriodDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwPeriodEntity lffwPeriod){
		lffwPeriodDao.save(lffwPeriod);
	}
	
	@Override
	public void update(LffwPeriodEntity lffwPeriod){
		lffwPeriodDao.update(lffwPeriod);
	}
	
	@Override
	public void delete(Integer id){
		lffwPeriodDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwPeriodDao.deleteBatch(ids);
	}
	
}
