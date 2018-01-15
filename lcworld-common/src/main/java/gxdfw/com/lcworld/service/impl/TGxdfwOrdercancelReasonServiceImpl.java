package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TGxdfwOrdercancelReasonDao;
import com.lcworld.entity.TGxdfwOrdercancelReasonEntity;
import com.lcworld.service.TGxdfwOrdercancelReasonService;



@Service("tGxdfwOrdercancelReasonService")
public class TGxdfwOrdercancelReasonServiceImpl implements TGxdfwOrdercancelReasonService {
	@Autowired
	private TGxdfwOrdercancelReasonDao tGxdfwOrdercancelReasonDao;
	
	@Override
	public TGxdfwOrdercancelReasonEntity queryObject(Integer id){
		return tGxdfwOrdercancelReasonDao.queryObject(id);
	}
	
	@Override
	public List<TGxdfwOrdercancelReasonEntity> queryList(Map<String, Object> map){
		return tGxdfwOrdercancelReasonDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tGxdfwOrdercancelReasonDao.queryTotal(map);
	}
	
	@Override
	public void save(TGxdfwOrdercancelReasonEntity tGxdfwOrdercancelReason){
		tGxdfwOrdercancelReasonDao.save(tGxdfwOrdercancelReason);
	}
	
	@Override
	public void update(TGxdfwOrdercancelReasonEntity tGxdfwOrdercancelReason){
		tGxdfwOrdercancelReasonDao.update(tGxdfwOrdercancelReason);
	}
	
	@Override
	public void delete(Integer id){
		tGxdfwOrdercancelReasonDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tGxdfwOrdercancelReasonDao.deleteBatch(ids);
	}
	
}
