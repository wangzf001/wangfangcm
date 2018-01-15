package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.UserDepositEntity;
import com.lcworld.utils.R;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-10 13:54:03
 */
public interface UserDepositService {
	
	UserDepositEntity queryObject(Integer id);
	
	List<UserDepositEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserDepositEntity userDeposit);
	
	void update(UserDepositEntity userDeposit);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 条件查询用户押金
	 * @param params
	 * @return
	 */
	UserDepositEntity queryByCondition(Map<String,Object> params);
	/**
	 * 删除用户押金
	 * @param params
	 * @return
	 */
	R deleteUserDeposit(JSONObject params);
}
