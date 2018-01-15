package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.HysfwRoomEquipmentDao;
import com.lcworld.entity.HysfwRoomEquipmentEntity;
import com.lcworld.service.HysfwRoomEquipmentService;



@Service("hysfwRoomEquipmentService")
public class HysfwRoomEquipmentServiceImpl implements HysfwRoomEquipmentService {
	@Autowired
	private HysfwRoomEquipmentDao hysfwRoomEquipmentDao;
	
	@Override
	public HysfwRoomEquipmentEntity queryObject(Integer id){
		return hysfwRoomEquipmentDao.queryObject(id);
	}
	
	@Override
	public List<HysfwRoomEquipmentEntity> queryList(Map<String, Object> map){
		return hysfwRoomEquipmentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return hysfwRoomEquipmentDao.queryTotal(map);
	}
	
	@Override
	public void save(HysfwRoomEquipmentEntity hysfwRoomEquipment){
		hysfwRoomEquipmentDao.save(hysfwRoomEquipment);
	}
	
	@Override
	public void update(HysfwRoomEquipmentEntity hysfwRoomEquipment){
		hysfwRoomEquipmentDao.update(hysfwRoomEquipment);
	}
	
	@Override
	public void delete(Integer id){
		hysfwRoomEquipmentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		hysfwRoomEquipmentDao.deleteBatch(ids);
	}
	
}
