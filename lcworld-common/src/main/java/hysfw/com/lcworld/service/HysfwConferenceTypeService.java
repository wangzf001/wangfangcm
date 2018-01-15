package com.lcworld.service;

import com.lcworld.entity.HysfwConferenceTypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-30 18:43:48
 */
public interface HysfwConferenceTypeService {
	
	HysfwConferenceTypeEntity queryObject(Integer id);
	
	List<HysfwConferenceTypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HysfwConferenceTypeEntity hysfwConferenceType);
	
	void update(HysfwConferenceTypeEntity hysfwConferenceType);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
