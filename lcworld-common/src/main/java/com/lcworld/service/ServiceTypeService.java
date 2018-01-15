package com.lcworld.service;

import com.lcworld.entity.ServiceTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 服务分类表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 15:00:55
 */
public interface ServiceTypeService {
	
	ServiceTypeEntity queryObject(Integer id);
	
	List<ServiceTypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ServiceTypeEntity serviceType);
	
	void update(ServiceTypeEntity serviceType);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
