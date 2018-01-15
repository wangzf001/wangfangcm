package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.HysfwConferenceEquipmentDao;
import com.lcworld.entity.HysfwConferenceEquipmentEntity;
import com.lcworld.service.HysfwConferenceEquipmentService;



@Service("hysfwConferenceEquipmentService")
public class HysfwConferenceEquipmentServiceImpl implements HysfwConferenceEquipmentService {
	@Autowired
	private HysfwConferenceEquipmentDao hysfwConferenceEquipmentDao;
	
	@Override
	public HysfwConferenceEquipmentEntity queryObject(Integer id){
		return hysfwConferenceEquipmentDao.queryObject(id);
	}
	
	@Override
	public List<HysfwConferenceEquipmentEntity> queryList(Map<String, Object> map){
		return hysfwConferenceEquipmentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return hysfwConferenceEquipmentDao.queryTotal(map);
	}
	
	@Override
	public void save(HysfwConferenceEquipmentEntity hysfwConferenceEquipment){
		hysfwConferenceEquipmentDao.save(hysfwConferenceEquipment);
	}
	
	@Override
	public void update(HysfwConferenceEquipmentEntity hysfwConferenceEquipment){
		hysfwConferenceEquipmentDao.update(hysfwConferenceEquipment);
	}
	
	@Override
	public void delete(Integer id){
		hysfwConferenceEquipmentDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		hysfwConferenceEquipmentDao.deleteBatch(ids);
	}
	
}
