package com.lcworld.service;

import com.lcworld.entity.TYytcStepEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
public interface TYytcStepService {
	
	TYytcStepEntity queryObject(Integer sId);
	
	List<TYytcStepEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TYytcStepEntity tYytcStep);
	
	void update(TYytcStepEntity tYytcStep);
	
	void delete(Integer sId);
	
	void deleteBatch(Integer[] sIds);
}
