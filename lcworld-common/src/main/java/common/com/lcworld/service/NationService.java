package com.lcworld.service;

import com.lcworld.entity.NationEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-11 10:35:19
 */
public interface NationService {
	
	NationEntity queryObject(Integer id);
	
	List<NationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(NationEntity nation);
	
	void update(NationEntity nation);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
