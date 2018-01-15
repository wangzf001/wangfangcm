package com.lcworld.service;

import com.lcworld.entity.UserWallelogEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户钱包记录
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:37
 */
public interface UserWallelogService {
	
	UserWallelogEntity queryObject(Integer id);
	
	List<UserWallelogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserWallelogEntity userWallelog);
	
	void update(UserWallelogEntity userWallelog);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
