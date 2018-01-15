package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.UserVisibyDao;
import com.lcworld.entity.UserVisibyEntity;
import com.lcworld.service.UserVisibyService;



@Service("userVisibyService")
public class UserVisibyServiceImpl implements UserVisibyService {
	@Autowired
	private UserVisibyDao userVisibyDao;
	
	@Override
	public UserVisibyEntity queryObject(Integer id){
		return userVisibyDao.queryObject(id);
	}
	
	@Override
	public List<UserVisibyEntity> queryList(Map<String, Object> map){
		return userVisibyDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return userVisibyDao.queryTotal(map);
	}
	
	@Override
	public void save(UserVisibyEntity userVisiby){
		userVisibyDao.save(userVisiby);
	}
	
	@Override
	public void update(UserVisibyEntity userVisiby){
		userVisibyDao.update(userVisiby);
	}
	
	@Override
	public void delete(Integer id){
		userVisibyDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		userVisibyDao.deleteBatch(ids);
	}

	@Override
	public void savebench(List<UserVisibyEntity> byuserlist) {
		userVisibyDao.savebench(byuserlist);
		
	}

	@Override
	public void deleteBypid(Integer id) {
		userVisibyDao.deleteBypid(id)	;	
	}

	@Override
	public void deleteByvlid(Integer id) {
		// TODO Auto-generated method stub
		userVisibyDao.deleteByvlid(id);
	}
	
}
