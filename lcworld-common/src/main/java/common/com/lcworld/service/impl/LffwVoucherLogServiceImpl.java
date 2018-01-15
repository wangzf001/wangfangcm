package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.LffwVoucherLogDao;
import com.lcworld.entity.LffwVoucherLogEntity;
import com.lcworld.service.LffwVoucherLogService;



@Service("lffwVoucherLogService")
public class LffwVoucherLogServiceImpl implements LffwVoucherLogService {
	@Autowired
	private LffwVoucherLogDao lffwVoucherLogDao;
	
	@Override
	public LffwVoucherLogEntity queryObject(Integer id){
		return lffwVoucherLogDao.queryObject(id);
	}
	
	@Override
	public List<LffwVoucherLogEntity> queryList(Map<String, Object> map){
		return lffwVoucherLogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwVoucherLogDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwVoucherLogEntity lffwVoucherLog){
		lffwVoucherLogDao.save(lffwVoucherLog);
	}
	
	@Override
	public void update(LffwVoucherLogEntity lffwVoucherLog){
		lffwVoucherLogDao.update(lffwVoucherLog);
	}
	
	@Override
	public void delete(Integer id){
		lffwVoucherLogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwVoucherLogDao.deleteBatch(ids);
	}
	
}
