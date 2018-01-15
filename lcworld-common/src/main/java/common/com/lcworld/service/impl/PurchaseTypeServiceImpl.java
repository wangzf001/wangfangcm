package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.PurchaseTypeDao;
import com.lcworld.entity.PurchaseTypeEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.service.PurchaseTypeService;
import com.lcworld.utils.Constant;



@Service("purchaseTypeService")
public class PurchaseTypeServiceImpl implements PurchaseTypeService {
	@Autowired
	private PurchaseTypeDao purchaseTypeDao;
	
	@Override
	public PurchaseTypeEntity queryObject(Integer id){
		return purchaseTypeDao.queryObject(id);
	}
	
	@Override
	public List<PurchaseTypeEntity> queryList(Map<String, Object> map){
		return purchaseTypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return purchaseTypeDao.queryTotal(map);
	}
	
	@Override
	public void save(PurchaseTypeEntity purchaseType){
		purchaseTypeDao.save(purchaseType);
	}
	
	@Override
	public void update(PurchaseTypeEntity purchaseType){
		purchaseTypeDao.update(purchaseType);
	}
	
	@Override
	public void delete(Integer id){
		purchaseTypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		purchaseTypeDao.deleteBatch(ids);
	}

	@Override
	public List<PurchaseTypeEntity> queryAllSysRolesByUid(Long userId) {
				JSONObject param = new JSONObject();
				param.put("uid", userId.intValue());
				if(  Constant.SUPER_ADMIN == userId.intValue() ){
					return purchaseTypeDao.queryAllSysRoles();
				}
				return purchaseTypeDao.queryAllSysRolesByUid(param);
	}
	
}
