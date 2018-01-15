package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.DsfwOrdercancelReasonDao;
import com.lcworld.entity.DsfwOrdercancelReasonEntity;
import com.lcworld.service.DsfwOrdercancelReasonService;



@Service("dsfwOrdercancelReasonService")
public class DsfwOrdercancelReasonServiceImpl implements DsfwOrdercancelReasonService {
	@Autowired
	private DsfwOrdercancelReasonDao dsfwOrdercancelReasonDao;
	
	@Override
	public DsfwOrdercancelReasonEntity queryObject(Integer id){
		return dsfwOrdercancelReasonDao.queryObject(id);
	}
	
	@Override
	public List<DsfwOrdercancelReasonEntity> queryList(Map<String, Object> map){
		return dsfwOrdercancelReasonDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dsfwOrdercancelReasonDao.queryTotal(map);
	}
	
	@Override
	public void save(DsfwOrdercancelReasonEntity dsfwOrdercancelReason){
		dsfwOrdercancelReasonDao.save(dsfwOrdercancelReason);
	}
	
	@Override
	public void update(DsfwOrdercancelReasonEntity dsfwOrdercancelReason){
		dsfwOrdercancelReasonDao.update(dsfwOrdercancelReason);
	}
	
	@Override
	public void delete(Integer id){
		dsfwOrdercancelReasonDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dsfwOrdercancelReasonDao.deleteBatch(ids);
	}
	
}
