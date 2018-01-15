package com.lcworld.service;

import com.lcworld.entity.HysfwRoomEquipmentEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-30 18:43:48
 */
public interface HysfwRoomEquipmentService {
	
	HysfwRoomEquipmentEntity queryObject(Integer id);
	
	List<HysfwRoomEquipmentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HysfwRoomEquipmentEntity hysfwRoomEquipment);
	
	void update(HysfwRoomEquipmentEntity hysfwRoomEquipment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
