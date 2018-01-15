package com.lcworld.service;

import com.lcworld.entity.UserWalleorderEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户钱包充值订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:38
 */
public interface UserWalleorderService extends IOrderService{
	
	UserWalleorderEntity queryObject(Integer id);
	
	List<UserWalleorderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserWalleorderEntity userWalleorder);
	
	void update(UserWalleorderEntity userWalleorder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
