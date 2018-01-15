package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.UserBankCardDao;
import com.lcworld.entity.UserBankCardEntity;
import com.lcworld.service.UserBankCardService;



@Service("userBankCardService")
public class UserBankCardServiceImpl implements UserBankCardService {
	@Autowired
	private UserBankCardDao userBankCardDao;
	
	@Override
	public UserBankCardEntity queryObject(Integer id){
		return userBankCardDao.queryObject(id);
	}
	
	@Override
	public List<UserBankCardEntity> queryList(Map<String, Object> map){
		return userBankCardDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userBankCardDao.queryTotal(map);
	}
	
	@Override
	public void save(UserBankCardEntity userBankCard){
		userBankCardDao.save(userBankCard);
	}
	
	@Override
	public void update(UserBankCardEntity userBankCard){
		userBankCardDao.update(userBankCard);
	}
	
	@Override
	public void delete(Integer id){
		userBankCardDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userBankCardDao.deleteBatch(ids);
	}
	
}
