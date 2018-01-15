package com.lcworld.service;

import com.lcworld.entity.LffwPositionEntity;

import java.util.List;
import java.util.Map;

/**
 * 理发服务职称
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 14:01:24
 */
public interface LffwPositionService {
	
	LffwPositionEntity queryObject(Integer id);
	
	List<LffwPositionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwPositionEntity lffwPosition);
	
	void update(LffwPositionEntity lffwPosition);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
