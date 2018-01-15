package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.HysfwConferenceEquipmentEntity;
import com.lcworld.entity.HysfwConferenceRoomEntity;
import com.lcworld.entity.HysfwConferenceServiceEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
public interface HysfwConferenceRoomDao extends BaseDao<HysfwConferenceRoomEntity> {

	List<HysfwConferenceEquipmentEntity> queryAvailableEquipmentList(Integer id);

	List<Map<Integer, String>> queryConferenceType();

	List<HysfwConferenceServiceEntity> queryAvailableServiceList(Integer id);

	void deleteByRoomid(Integer id);

	void saveRoomSE(List<Map<String, Object>> list);
	
}
