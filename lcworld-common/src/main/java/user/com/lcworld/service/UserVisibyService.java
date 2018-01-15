package com.lcworld.service;

import com.lcworld.entity.UserVisibyEntity;
import com.lcworld.entity.VisiuserEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-16 15:52:15
 */
public interface UserVisibyService {
	
	UserVisibyEntity queryObject(Integer id);
	
	List<UserVisibyEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserVisibyEntity userVisiby);
	
	void update(UserVisibyEntity userVisiby);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void savebench(List<UserVisibyEntity> byuserlist);

	void deleteBypid(Integer id);

	void deleteByvlid(Integer id);

}
