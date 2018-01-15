package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.UserCaptchaDao;
import com.lcworld.entity.UserCaptchaEntity;
import com.lcworld.service.UserCaptchaService;



@Service("userCaptchaService")
public class UserCaptchaServiceImpl implements UserCaptchaService {
	@Autowired
	private UserCaptchaDao userCaptchaDao;
	
	@Override
	public UserCaptchaEntity queryObject(Integer id){
		return userCaptchaDao.queryObject(id);
	}
	
	@Override
	public List<UserCaptchaEntity> queryList(Map<String, Object> map){
		return userCaptchaDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userCaptchaDao.queryTotal(map);
	}
	
	@Override
	public void save(UserCaptchaEntity userCaptcha){
		userCaptchaDao.save(userCaptcha);
	}
	
	@Override
	public void update(UserCaptchaEntity userCaptcha){
		userCaptchaDao.update(userCaptcha);
	}
	
	@Override
	public void delete(Integer id){
		userCaptchaDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userCaptchaDao.deleteBatch(ids);
	}
	
}
