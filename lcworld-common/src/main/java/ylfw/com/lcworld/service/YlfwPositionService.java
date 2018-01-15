package com.lcworld.service;

import com.lcworld.entity.YlfwPositionEntity;

import java.util.List;
import java.util.Map;

/**
 * 医疗服务--职称
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-20 13:09:47
 */
public interface YlfwPositionService {
	
	YlfwPositionEntity queryObject(Integer id);
	
	List<YlfwPositionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwPositionEntity ylfwPosition);
	
	void update(YlfwPositionEntity ylfwPosition);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
