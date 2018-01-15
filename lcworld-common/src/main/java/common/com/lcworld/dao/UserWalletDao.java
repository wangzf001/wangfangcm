package com.lcworld.dao;

import com.lcworld.entity.UserWalleorderEntity;
import com.lcworld.entity.UserWalletEntity;

/**
 * 用户钱包
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:38
 */
public interface UserWalletDao extends BaseDao<UserWalletEntity> {

	UserWalletEntity queryByUid(Integer uid);

	void updateRemainByUid(UserWalleorderEntity order);
	
}
