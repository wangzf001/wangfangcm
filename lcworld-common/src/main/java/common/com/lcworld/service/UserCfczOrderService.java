package com.lcworld.service;

import com.lcworld.entity.UserCfczOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户餐卡充值订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-23 11:08:21
 */
public interface UserCfczOrderService extends IOrderService{
	
	UserCfczOrderEntity queryObject(Integer id);
	
	List<UserCfczOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserCfczOrderEntity userCfczOrder);
	
	void update(UserCfczOrderEntity userCfczOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
