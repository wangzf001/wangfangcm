package com.lcworld.service;

import com.lcworld.entity.LffwServiceitemEntity;

import java.util.List;
import java.util.Map;

/**
 * 理发服务-服务项目
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:13
 */
public interface LffwServiceitemService {
	
	LffwServiceitemEntity queryObject(Integer id);
	
	List<LffwServiceitemEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwServiceitemEntity lffwServiceitem);
	
	void update(LffwServiceitemEntity lffwServiceitem);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
