package com.lcworld.dao;

import java.util.Map;

import com.lcworld.entity.UserCfEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-09 15:27:08
 */
public interface UserCfDao extends BaseDao<UserCfEntity> {

	UserCfEntity queryByCondition(Map<String, Object> params);
	
}
