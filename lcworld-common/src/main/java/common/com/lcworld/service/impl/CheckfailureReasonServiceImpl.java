package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.CheckfailureReasonDao;
import com.lcworld.entity.CheckfailureReasonEntity;
import com.lcworld.service.CheckfailureReasonService;



@Service("checkfailureReasonService")
public class CheckfailureReasonServiceImpl implements CheckfailureReasonService {
	@Autowired
	private CheckfailureReasonDao checkfailureReasonDao;
	
	@Override
	public CheckfailureReasonEntity queryObject(Integer id){
		return checkfailureReasonDao.queryObject(id);
	}
	
	@Override
	public List<CheckfailureReasonEntity> queryList(Map<String, Object> map){
		return checkfailureReasonDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return checkfailureReasonDao.queryTotal(map);
	}
	
	@Override
	public void save(CheckfailureReasonEntity checkfailureReason){
		checkfailureReasonDao.save(checkfailureReason);
	}
	
	@Override
	public void update(CheckfailureReasonEntity checkfailureReason){
		checkfailureReasonDao.update(checkfailureReason);
	}
	
	@Override
	public void delete(Integer id){
		checkfailureReasonDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		checkfailureReasonDao.deleteBatch(ids);
	}
	
}
