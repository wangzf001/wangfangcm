package com.lcworld.service;

import com.lcworld.entity.TCarCartypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 车辆类型
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-28 17:22:36
 */
public interface TCarCartypeService {
	
	TCarCartypeEntity queryObject(Integer id);
	
	List<TCarCartypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TCarCartypeEntity tCarCartype);
	
	void update(TCarCartypeEntity tCarCartype);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
