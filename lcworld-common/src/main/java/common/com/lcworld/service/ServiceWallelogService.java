package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.ServiceWallelogEntity;
import com.lcworld.vo.service.owner.OwnerWalletHome;
import com.lcworld.vo.service.owner.ServiceOwnerWalletLog;

import java.util.List;
import java.util.Map;

/**
 * 服务端钱包明细表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-18 16:06:23
 */
public interface ServiceWallelogService {
	
	ServiceWallelogEntity queryObject(Integer id);
	
	List<ServiceWallelogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ServiceWallelogEntity serviceWallelog);
	
	void update(ServiceWallelogEntity serviceWallelog);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	Double walletHome(JSONObject p);

	List<ServiceOwnerWalletLog> walletDetail(JSONObject p);
}
