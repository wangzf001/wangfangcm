package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.LffwServiceitemDao;
import com.lcworld.entity.LffwServiceitemEntity;
import com.lcworld.service.LffwServiceitemService;



@Service("lffwServiceitemService")
public class LffwServiceitemServiceImpl implements LffwServiceitemService {
	@Autowired
	private LffwServiceitemDao lffwServiceitemDao;
	
	@Override
	public LffwServiceitemEntity queryObject(Integer id){
		return lffwServiceitemDao.queryObject(id);
	}
	
	@Override
	public List<LffwServiceitemEntity> queryList(Map<String, Object> map){
		return lffwServiceitemDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwServiceitemDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwServiceitemEntity lffwServiceitem){
		lffwServiceitemDao.save(lffwServiceitem);
	}
	
	@Override
	public void update(LffwServiceitemEntity lffwServiceitem){
		lffwServiceitemDao.update(lffwServiceitem);
	}
	
	@Override
	public void delete(Integer id){
		lffwServiceitemDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwServiceitemDao.deleteBatch(ids);
	}
	
}
