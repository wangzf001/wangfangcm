package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.UserCfDao;
import com.lcworld.entity.UserCfEntity;
import com.lcworld.service.UserCfService;



@Service("userCfService")
public class UserCfServiceImpl implements UserCfService {
	@Autowired
	private UserCfDao userCfDao;
	
	@Override
	public UserCfEntity queryObject(Integer id){
		return userCfDao.queryObject(id);
	}
	
	@Override
	public List<UserCfEntity> queryList(Map<String, Object> map){
		return userCfDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userCfDao.queryTotal(map);
	}
	
	@Override
	public void save(UserCfEntity userCf){
		userCfDao.save(userCf);
	}
	
	@Override
	public void update(UserCfEntity userCf){
		userCfDao.update(userCf);
	}
	
	@Override
	public void delete(Integer id){
		userCfDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userCfDao.deleteBatch(ids);
	}

	@Override
	public UserCfEntity queryByCondition(Map<String,Object> params) {
		return userCfDao.queryByCondition(params);
	}
	
}
