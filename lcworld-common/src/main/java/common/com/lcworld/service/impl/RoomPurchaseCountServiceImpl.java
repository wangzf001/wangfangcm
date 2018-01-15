package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.RoomPurchaseCountDao;
import com.lcworld.entity.RoomPurchaseCountEntity;
import com.lcworld.service.RoomPurchaseCountService;



@Service("roomPurchaseCountService")
public class RoomPurchaseCountServiceImpl implements RoomPurchaseCountService {
	@Autowired
	private RoomPurchaseCountDao roomPurchaseCountDao;
	
	@Override
	public RoomPurchaseCountEntity queryObject(Integer id){
		return roomPurchaseCountDao.queryObject(id);
	}
	
	@Override
	public List<RoomPurchaseCountEntity> queryList(Map<String, Object> map){
		return roomPurchaseCountDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return roomPurchaseCountDao.queryTotal(map);
	}
	
	@Override
	public void save(RoomPurchaseCountEntity roomPurchaseCount){
		roomPurchaseCountDao.save(roomPurchaseCount);
	}
	
	@Override
	public void update(RoomPurchaseCountEntity roomPurchaseCount){
		roomPurchaseCountDao.update(roomPurchaseCount);
	}
	
	@Override
	public void delete(Integer id){
		roomPurchaseCountDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		roomPurchaseCountDao.deleteBatch(ids);
	}
	
}
