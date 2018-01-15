package com.lcworld.dao;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.PurchaseAccountEntity;
 
/**
 * 采购账户
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:37
 */
public interface PurchaseAccountDao extends BaseDao<PurchaseAccountEntity> {

    PurchaseAccountEntity queryGroupAcount(JSONObject param);
	
}
