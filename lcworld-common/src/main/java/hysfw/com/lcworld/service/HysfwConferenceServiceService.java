package com.lcworld.service;

import com.lcworld.entity.HysfwConferenceServiceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-30 16:17:34
 */
public interface HysfwConferenceServiceService {
	
	HysfwConferenceServiceEntity queryObject(Integer id);
	
	List<HysfwConferenceServiceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HysfwConferenceServiceEntity hysfwConferenceService);
	
	void update(HysfwConferenceServiceEntity hysfwConferenceService);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
