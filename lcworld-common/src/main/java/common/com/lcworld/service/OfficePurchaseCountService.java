package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.OfficePurchaseCountEntity;
import com.lcworld.entity.UserPurchaseLimitEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 处室对公账户
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-13 10:33:57
 */
public interface OfficePurchaseCountService extends IPurchaseCountService {
	
	OfficePurchaseCountEntity queryObject(Integer id);
	
	List<OfficePurchaseCountEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OfficePurchaseCountEntity officePurchaseCount);
	
	void update(OfficePurchaseCountEntity officePurchaseCount);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<OfficePurchaseCountEntity> queryOPCList(Query query);

    int queryOPCTotal(Query query);

    List<OfficePurchaseCountEntity> queryFOPCList(JSONObject obj);

    List<UserPurchaseLimitEntity> depart(Map<String, Object> params);
    
    int departTotal(Query query);
}
