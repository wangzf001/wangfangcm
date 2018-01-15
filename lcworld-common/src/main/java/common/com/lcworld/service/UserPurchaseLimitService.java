package com.lcworld.service;

import com.lcworld.entity.UserPurchaseLimitEntity;
import com.lcworld.utils.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 用户账户额度限制
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-13 10:08:45
 */
public interface UserPurchaseLimitService {
	
	UserPurchaseLimitEntity queryObject(Integer id);
	
	List<UserPurchaseLimitEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserPurchaseLimitEntity userPurchaseLimit);
	
	void update(UserPurchaseLimitEntity userPurchaseLimit);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<UserPurchaseLimitEntity> queryUPList(Query query);

    int queryUPTotal(Query query);

    List<UserPurchaseLimitEntity> queryList1(Map<String, Object> params);

    /**
     * @param uid
     * @param paymoney
     * @param usetype : 1: 使用（出），2：退（入）  
     * @param servicecode 服务类型
     */
    void savePurchaselimit(Integer uid, BigDecimal paymoney, int servicecode, int usetype);

    List<UserPurchaseLimitEntity> querylimitList(Query q);

    int querylimitTotal(Map<String, Object> params);

    void deleteBatchByuid(Map<String,Object> map);

    /**
     * @param purchaseid 账户id
     * @param type 账户级别 1： 司局，2： 处室
     * @return 已分配的结果
     */
    List<UserPurchaseLimitEntity> depart(Map<String, Object> params, Integer type);
    
    int departTotal(Query query,Integer type);

	void saveBatch(List<UserPurchaseLimitEntity> uplmtlist);
}
