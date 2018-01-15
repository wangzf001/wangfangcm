package com.lcworld.service;

import com.lcworld.entity.UserPurchaseLimilogEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 用户对公账户额度日志表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-26 17:31:54
 */
public interface UserPurchaseLimilogService {
	
	UserPurchaseLimilogEntity queryObject(Integer id);
	
	List<UserPurchaseLimilogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserPurchaseLimilogEntity userPurchaseLimilog);
	
	void update(UserPurchaseLimilogEntity userPurchaseLimilog);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void savelimitlog(Integer limitid,Integer uid, int servicecode, BigDecimal paymoney, int usetype);
}
