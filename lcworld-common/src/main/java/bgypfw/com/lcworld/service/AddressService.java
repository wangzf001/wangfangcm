package com.lcworld.service;

import com.lcworld.entity.AddressEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 19:32:34
 */
public interface AddressService {
	
	AddressEntity queryObject(Integer id);
	
	List<AddressEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AddressEntity bgypfwAddress);
	
	void update(AddressEntity bgypfwAddress);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
