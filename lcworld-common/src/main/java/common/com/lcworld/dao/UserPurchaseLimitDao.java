package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.UserPurchaseLimitEntity;
import com.lcworld.utils.Query;

/**
 * 用户账户额度限制
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-13 10:08:45
 */
public interface UserPurchaseLimitDao extends BaseDao<UserPurchaseLimitEntity> {

	List<UserPurchaseLimitEntity> queryUPList(Query query);

	List<UserPurchaseLimitEntity> queryUserNoLimitList(Query query);
	
	int queryUserNoLimitTotal(Query query);

	int queryUPTotal(Query query);

	List<UserPurchaseLimitEntity> queryList1(Map<String, Object> params);

	List<UserPurchaseLimitEntity> queryUserLimitList(JSONObject obj);

	List<UserPurchaseLimitEntity> querylimitList(Query q);

	int querylimitTotal(Map<String, Object> params);

	void deleteBatchByList(List<UserPurchaseLimitEntity> list);

	void deleteBatchByuid(Map<String, Object> map);

}
