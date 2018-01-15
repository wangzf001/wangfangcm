package com.lcworld.service;

import com.lcworld.entity.TdhdActivityDemandEntity;

import java.util.List;
import java.util.Map;

/**
 * 活动需求表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-22 18:59:09
 */
public interface TdhdActivityDemandService {
	
	TdhdActivityDemandEntity queryObject(Integer id);
	
	List<TdhdActivityDemandEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TdhdActivityDemandEntity tdhqActivityDemand);
	
	void update(TdhdActivityDemandEntity tdhqActivityDemand);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
