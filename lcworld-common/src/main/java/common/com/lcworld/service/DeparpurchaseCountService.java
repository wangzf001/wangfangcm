package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.DeparpurchaseCountEntity;
import com.lcworld.entity.UserPurchaseLimitEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-13 10:33:57
 */
public interface DeparpurchaseCountService extends IPurchaseCountService{
	
	DeparpurchaseCountEntity queryObject(Integer id);
	
	List<DeparpurchaseCountEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DeparpurchaseCountEntity deparpurchaseCount);
	
	void update(DeparpurchaseCountEntity deparpurchaseCount);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<DeparpurchaseCountEntity> queryDPCList(Query query);

    int queryDPCTotal(Query query);

    List<DeparpurchaseCountEntity> queryFDPCList(JSONObject obj);

    List<UserPurchaseLimitEntity> depart(Map<String, Object> params);
    
    int departTotal(Query query);
}
