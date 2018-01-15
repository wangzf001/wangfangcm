package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.UserWallelogDao;
import com.lcworld.entity.UserWallelogEntity;
import com.lcworld.service.UserWallelogService;



@Service("userWallelogService")
public class UserWallelogServiceImpl implements UserWallelogService {
	@Autowired
	private UserWallelogDao userWallelogDao;
	
	@Override
	public UserWallelogEntity queryObject(Integer id){
		return userWallelogDao.queryObject(id);
	}
	
	@Override
	public List<UserWallelogEntity> queryList(Map<String, Object> map){
		return userWallelogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userWallelogDao.queryTotal(map);
	}
	
	@Override
	public void save(UserWallelogEntity userWallelog){
		userWallelogDao.save(userWallelog);
	}
	
	@Override
	public void update(UserWallelogEntity userWallelog){
		userWallelogDao.update(userWallelog);
	}
	
	@Override
	public void delete(Integer id){
		userWallelogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userWallelogDao.deleteBatch(ids);
	}
	
}
