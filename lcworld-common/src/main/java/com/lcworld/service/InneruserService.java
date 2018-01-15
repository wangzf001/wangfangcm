package com.lcworld.service;

import com.lcworld.entity.InneruserEntity;

import java.util.List;
import java.util.Map;

/**
 * 内部人员表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-25 11:54:31
 */
public interface InneruserService {
	
	InneruserEntity queryObject(Integer id);
	
	List<InneruserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(InneruserEntity inneruser);
	
	void update(InneruserEntity inneruser);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
