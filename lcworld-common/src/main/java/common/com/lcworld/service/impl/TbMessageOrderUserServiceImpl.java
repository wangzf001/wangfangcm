package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TbMessageOrderUserDao;
import com.lcworld.entity.TbMessageOrderUserEntity;
import com.lcworld.service.TbMessageOrderUserService;



@Service("tbMessageOrderUserService")
public class TbMessageOrderUserServiceImpl implements TbMessageOrderUserService {
	@Autowired
	private TbMessageOrderUserDao tbMessageOrderUserDao;
	
	@Override
	public TbMessageOrderUserEntity queryObject(Long autoId){
		return tbMessageOrderUserDao.queryObject(autoId);
	}
	
	@Override
	public List<TbMessageOrderUserEntity> queryList(Map<String, Object> map){
		return tbMessageOrderUserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tbMessageOrderUserDao.queryTotal(map);
	}
	
	@Override
	public void save(TbMessageOrderUserEntity tbMessageOrderUser){
		tbMessageOrderUserDao.save(tbMessageOrderUser);
	}
	
	@Override
	public void update(TbMessageOrderUserEntity tbMessageOrderUser){
		tbMessageOrderUserDao.update(tbMessageOrderUser);
	}
	
	@Override
	public void delete(Long autoId){
		tbMessageOrderUserDao.delete(autoId);
	}
	
	@Override
	public void deleteBatch(Long[] autoIds){
		tbMessageOrderUserDao.deleteBatch(autoIds);
	}
	
}
