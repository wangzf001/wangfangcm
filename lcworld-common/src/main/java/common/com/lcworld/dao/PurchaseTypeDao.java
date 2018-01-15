package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.PurchaseTypeEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-30 15:30:18
 */
public interface PurchaseTypeDao extends BaseDao<PurchaseTypeEntity> {

	List<PurchaseTypeEntity> queryAllSysRoles();

	List<PurchaseTypeEntity> queryAllSysRolesByUid(JSONObject param);
	
}
