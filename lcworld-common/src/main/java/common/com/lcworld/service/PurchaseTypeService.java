package com.lcworld.service;

import com.lcworld.entity.PurchaseTypeEntity;
import com.lcworld.entity.ServiceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-30 15:30:18
 */
public interface PurchaseTypeService {
	
	PurchaseTypeEntity queryObject(Integer id);
	
	List<PurchaseTypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PurchaseTypeEntity purchaseType);
	
	void update(PurchaseTypeEntity purchaseType);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<PurchaseTypeEntity> queryAllSysRolesByUid(Long userId);
}
