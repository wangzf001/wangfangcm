package com.lcworld.dao;

import java.util.Map;

import com.lcworld.entity.UserDepositEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-10 13:54:03
 */
public interface UserDepositDao extends BaseDao<UserDepositEntity> {

	UserDepositEntity queryByCondition(Map<String, Object> params);
	
}
