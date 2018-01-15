package com.lcworld.dao;

import java.util.List;

import com.lcworld.entity.UserVisibyEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-16 15:52:15
 */
public interface UserVisibyDao extends BaseDao<UserVisibyEntity> {

	void savebench(List<UserVisibyEntity> byuserlist);

	void deleteBypid(Integer id);

	void deleteByvlid(Integer id);
	
}
