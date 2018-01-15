package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.TNoticeDao;
import com.lcworld.entity.TNoticeEntity;
import com.lcworld.service.TNoticeService;
import com.lcworld.utils.Query;



@Service("tNoticeService")
public class TNoticeServiceImpl implements TNoticeService {
	@Autowired
	private TNoticeDao tNoticeDao;
	
	@Override
	public TNoticeEntity queryObject(Integer id){
		return tNoticeDao.queryObject(id);
	}
	
	@Override
	public List<TNoticeEntity> queryList(Map<String, Object> map){
		return tNoticeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tNoticeDao.queryTotal(map);
	}
	
	@Override
	public void save(TNoticeEntity tNotice){
		tNoticeDao.save(tNotice);
	}
	
	@Override
	public void update(TNoticeEntity tNotice){
		tNoticeDao.update(tNotice);
	}
	
	@Override
	public void delete(Integer id){
		tNoticeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tNoticeDao.deleteBatch(ids);
	}

	@Override
	public List<TNoticeEntity> queryFavorList(Query params) {
		return queryList(params);
	}
	
}
