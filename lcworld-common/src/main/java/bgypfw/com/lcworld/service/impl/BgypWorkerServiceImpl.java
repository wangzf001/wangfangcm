package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BgypWorkerDao;
import com.lcworld.entity.BgypWorkerEntity;
import com.lcworld.service.BgypWorkerService;



@Service("bgypWorkerService")
public class BgypWorkerServiceImpl implements BgypWorkerService {
	@Autowired
	private BgypWorkerDao bgypWorkerDao;
	
	@Override
	public BgypWorkerEntity queryObject(Integer id){
		return bgypWorkerDao.queryObject(id);
	}
	
	@Override
	public List<BgypWorkerEntity> queryList(Map<String, Object> map){
		return bgypWorkerDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypWorkerDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypWorkerEntity bgypWorker){
		bgypWorkerDao.save(bgypWorker);
	}
	
	@Override
	public void update(BgypWorkerEntity bgypWorker){
		bgypWorkerDao.update(bgypWorker);
	}
	
	@Override
	public void delete(Integer id){
		bgypWorkerDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypWorkerDao.deleteBatch(ids);
	}
	
}
