package com.lcworld.service;

import com.lcworld.entity.HysfwConferenceEquipmentEntity;
import com.lcworld.entity.HysfwConferenceRoomEntity;
import com.lcworld.entity.HysfwConferenceServiceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
public interface HysfwConferenceRoomService {
	
	HysfwConferenceRoomEntity queryObject(Integer id);
	
	List<HysfwConferenceRoomEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HysfwConferenceRoomEntity hysfwConferenceRoom);
	
	void update(HysfwConferenceRoomEntity hysfwConferenceRoom);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<HysfwConferenceEquipmentEntity> queryAvailableEquipmentList(Integer id);

	List<Map<Integer, String>> queryConferenceType();

	List<HysfwConferenceServiceEntity> queryAvailableServiceList(Integer id);
}
