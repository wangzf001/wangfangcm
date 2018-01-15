package com.lcworld.service;

import com.lcworld.entity.RoomEntity;

import java.util.List;
import java.util.Map;

/**
 * 基础数据-办公室
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:38
 */
public interface RoomService {
	
	RoomEntity queryObject(Integer id);
	
	List<RoomEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RoomEntity room);
	
	void update(RoomEntity room);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
