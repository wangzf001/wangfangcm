package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BgypfwOrdercancelReasonDao;
import com.lcworld.entity.BgypfwOrdercancelReasonEntity;
import com.lcworld.service.BgypfwOrdercancelReasonService;



@Service("bgypfwOrdercancelReasonService")
public class BgypfwOrdercancelReasonServiceImpl implements BgypfwOrdercancelReasonService {
	@Autowired
	private BgypfwOrdercancelReasonDao bgypfwOrdercancelReasonDao;
	
	@Override
	public BgypfwOrdercancelReasonEntity queryObject(Integer id){
		return bgypfwOrdercancelReasonDao.queryObject(id);
	}
	
	@Override
	public List<BgypfwOrdercancelReasonEntity> queryList(Map<String, Object> map){
		return bgypfwOrdercancelReasonDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwOrdercancelReasonDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwOrdercancelReasonEntity bgypfwOrdercancelReason){
		bgypfwOrdercancelReasonDao.save(bgypfwOrdercancelReason);
	}
	
	@Override
	public void update(BgypfwOrdercancelReasonEntity bgypfwOrdercancelReason){
		bgypfwOrdercancelReasonDao.update(bgypfwOrdercancelReason);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwOrdercancelReasonDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwOrdercancelReasonDao.deleteBatch(ids);
	}
	
}
