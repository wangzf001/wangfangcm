package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TDcfwOrdercancelReasonDao;
import com.lcworld.entity.TDcfwOrdercancelReasonEntity;
import com.lcworld.service.TDcfwOrdercancelReasonService;



@Service("tDcfwOrdercancelReasonService")
public class TDcfwOrdercancelReasonServiceImpl implements TDcfwOrdercancelReasonService {
	@Autowired
	private TDcfwOrdercancelReasonDao tDcfwOrdercancelReasonDao;
	
	@Override
	public TDcfwOrdercancelReasonEntity queryObject(Integer id){
		return tDcfwOrdercancelReasonDao.queryObject(id);
	}
	
	@Override
	public List<TDcfwOrdercancelReasonEntity> queryList(Map<String, Object> map){
		return tDcfwOrdercancelReasonDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tDcfwOrdercancelReasonDao.queryTotal(map);
	}
	
	@Override
	public void save(TDcfwOrdercancelReasonEntity tDcfwOrdercancelReason){
		tDcfwOrdercancelReasonDao.save(tDcfwOrdercancelReason);
	}
	
	@Override
	public void update(TDcfwOrdercancelReasonEntity tDcfwOrdercancelReason){
		tDcfwOrdercancelReasonDao.update(tDcfwOrdercancelReason);
	}
	
	@Override
	public void delete(Integer id){
		tDcfwOrdercancelReasonDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tDcfwOrdercancelReasonDao.deleteBatch(ids);
	}
	
}
