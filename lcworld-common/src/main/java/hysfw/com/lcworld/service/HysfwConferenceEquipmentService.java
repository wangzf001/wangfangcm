package com.lcworld.service;

import com.lcworld.entity.HysfwConferenceEquipmentEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
public interface HysfwConferenceEquipmentService {
	
	HysfwConferenceEquipmentEntity queryObject(Integer id);
	
	List<HysfwConferenceEquipmentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HysfwConferenceEquipmentEntity hysfwConferenceEquipment);
	
	void update(HysfwConferenceEquipmentEntity hysfwConferenceEquipment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
