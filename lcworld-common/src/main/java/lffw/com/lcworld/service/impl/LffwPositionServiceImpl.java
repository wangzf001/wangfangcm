package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.LffwPositionDao;
import com.lcworld.entity.LffwPositionEntity;
import com.lcworld.service.LffwPositionService;



@Service("lffwPositionService")
public class LffwPositionServiceImpl implements LffwPositionService {
	@Autowired
	private LffwPositionDao lffwPositionDao;
	
	@Override
	public LffwPositionEntity queryObject(Integer id){
		return lffwPositionDao.queryObject(id);
	}
	
	@Override
	public List<LffwPositionEntity> queryList(Map<String, Object> map){
		return lffwPositionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwPositionDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwPositionEntity lffwPosition){
		lffwPositionDao.save(lffwPosition);
	}
	
	@Override
	public void update(LffwPositionEntity lffwPosition){
		lffwPositionDao.update(lffwPosition);
	}
	
	@Override
	public void delete(Integer id){
		lffwPositionDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwPositionDao.deleteBatch(ids);
	}
	
}
