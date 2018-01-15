package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.HysfwConferenceServiceDao;
import com.lcworld.entity.HysfwConferenceServiceEntity;
import com.lcworld.service.HysfwConferenceServiceService;



@Service("hysfwConferenceServiceService")
public class HysfwConferenceServiceServiceImpl implements HysfwConferenceServiceService {
	@Autowired
	private HysfwConferenceServiceDao hysfwConferenceServiceDao;
	
	@Override
	public HysfwConferenceServiceEntity queryObject(Integer id){
		return hysfwConferenceServiceDao.queryObject(id);
	}
	
	@Override
	public List<HysfwConferenceServiceEntity> queryList(Map<String, Object> map){
		return hysfwConferenceServiceDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return hysfwConferenceServiceDao.queryTotal(map);
	}
	
	@Override
	public void save(HysfwConferenceServiceEntity hysfwConferenceService){
		hysfwConferenceServiceDao.save(hysfwConferenceService);
	}
	
	@Override
	public void update(HysfwConferenceServiceEntity hysfwConferenceService){
		hysfwConferenceServiceDao.update(hysfwConferenceService);
	}
	
	@Override
	public void delete(Integer id){
		hysfwConferenceServiceDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		hysfwConferenceServiceDao.deleteBatch(ids);
	}
	
}
