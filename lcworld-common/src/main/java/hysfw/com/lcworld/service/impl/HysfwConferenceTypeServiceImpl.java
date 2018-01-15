package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.HysfwConferenceTypeDao;
import com.lcworld.entity.HysfwConferenceTypeEntity;
import com.lcworld.service.HysfwConferenceTypeService;



@Service("hysfwConferenceTypeService")
public class HysfwConferenceTypeServiceImpl implements HysfwConferenceTypeService {
	@Autowired
	private HysfwConferenceTypeDao hysfwConferenceTypeDao;
	
	@Override
	public HysfwConferenceTypeEntity queryObject(Integer id){
		return hysfwConferenceTypeDao.queryObject(id);
	}
	
	@Override
	public List<HysfwConferenceTypeEntity> queryList(Map<String, Object> map){
		return hysfwConferenceTypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return hysfwConferenceTypeDao.queryTotal(map);
	}
	
	@Override
	public void save(HysfwConferenceTypeEntity hysfwConferenceType){
		hysfwConferenceTypeDao.save(hysfwConferenceType);
	}
	
	@Override
	public void update(HysfwConferenceTypeEntity hysfwConferenceType){
		hysfwConferenceTypeDao.update(hysfwConferenceType);
	}
	
	@Override
	public void delete(Integer id){
		hysfwConferenceTypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		hysfwConferenceTypeDao.deleteBatch(ids);
	}
	
}
