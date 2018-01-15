package com.lcworld.dao;

import com.lcworld.entity.AddressEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 19:32:34
 */
public interface AddressDao extends BaseDao<AddressEntity> {
	/**
	 * 清除用户所有地址的默认状态
	 * @param uid
	 */
	void clearAllDefault(Integer uid);
	
}
