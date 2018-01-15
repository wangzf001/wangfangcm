package com.lcworld.service;

import com.lcworld.entity.TUserPositionEntity;

import java.util.List;
import java.util.Map;

/**
 * 职位表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 14:23:42
 */
public interface TUserPositionService {
	
	TUserPositionEntity queryObject(Integer id);
	
	List<TUserPositionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TUserPositionEntity tUserPosition);
	
	void update(TUserPositionEntity tUserPosition);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
