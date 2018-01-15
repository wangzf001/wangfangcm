package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.RoomDao;
import com.lcworld.entity.RoomEntity;
import com.lcworld.service.RoomService;



@Service("roomService")
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomDao roomDao;
	
	@Override
	public RoomEntity queryObject(Integer id){
		return roomDao.queryObject(id);
	}
	
	@Override
	public List<RoomEntity> queryList(Map<String, Object> map){
		return roomDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return roomDao.queryTotal(map);
	}
	
	@Override
	public void save(RoomEntity room){
		roomDao.save(room);
	}
	
	@Override
	public void update(RoomEntity room){
		roomDao.update(room);
	}
	
	@Override
	public void delete(Integer id){
		roomDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		roomDao.deleteBatch(ids);
	}
	
}
