package com.lcworld.service;

import com.lcworld.entity.UserBankCardEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户银行卡
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:38
 */
public interface UserBankCardService {
	
	UserBankCardEntity queryObject(Integer id);
	
	List<UserBankCardEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserBankCardEntity userBankCard);
	
	void update(UserBankCardEntity userBankCard);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
