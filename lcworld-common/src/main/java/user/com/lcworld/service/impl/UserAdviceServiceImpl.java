package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.UserAdviceDao;
import com.lcworld.entity.UserAdviceEntity;
import com.lcworld.service.UserAdviceService;



@Service("userAdviceService")
public class UserAdviceServiceImpl implements UserAdviceService {
	@Autowired
	private UserAdviceDao userAdviceDao;
	
	@Override
	public UserAdviceEntity queryObject(Integer id){
		return userAdviceDao.queryObject(id);
	}
	
	@Override
	public List<UserAdviceEntity> queryList(Map<String, Object> map){
		return userAdviceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userAdviceDao.queryTotal(map);
	}
	
	@Override
	public void save(UserAdviceEntity userAdvice){
		userAdviceDao.save(userAdvice);
	}
	
	@Override
	public void update(UserAdviceEntity userAdvice){
		userAdviceDao.update(userAdvice);
	}
	
	@Override
	public void delete(Integer id){
		userAdviceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userAdviceDao.deleteBatch(ids);
	}
	
}
