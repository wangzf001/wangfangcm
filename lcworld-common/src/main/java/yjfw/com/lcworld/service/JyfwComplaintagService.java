package com.lcworld.service;

import com.lcworld.entity.JyfwComplaintagEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 16:22:08
 */
public interface JyfwComplaintagService {
	
	JyfwComplaintagEntity queryObject(Integer id);
	
	List<JyfwComplaintagEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(JyfwComplaintagEntity jyfwComplaintag);
	
	void update(JyfwComplaintagEntity jyfwComplaintag);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
