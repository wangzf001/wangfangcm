package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TNoticeUserinterestDao;
import com.lcworld.entity.TNoticeUserinterestEntity;
import com.lcworld.service.TNoticeUserinterestService;



@Service("tNoticeUserinterestService")
public class TNoticeUserinterestServiceImpl implements TNoticeUserinterestService {
	@Autowired
	private TNoticeUserinterestDao tNoticeUserinterestDao;
	
	@Override
	public TNoticeUserinterestEntity queryObject(Integer id){
		return tNoticeUserinterestDao.queryObject(id);
	}
	
	@Override
	public List<TNoticeUserinterestEntity> queryList(Map<String, Object> map){
		return tNoticeUserinterestDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tNoticeUserinterestDao.queryTotal(map);
	}
	
	@Override
	public void save(TNoticeUserinterestEntity tNoticeUserinterest){
		tNoticeUserinterestDao.save(tNoticeUserinterest);
	}
	
	@Override
	public void update(TNoticeUserinterestEntity tNoticeUserinterest){
		tNoticeUserinterestDao.update(tNoticeUserinterest);
	}
	
	@Override
	public void delete(Integer id){
		tNoticeUserinterestDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tNoticeUserinterestDao.deleteBatch(ids);
	}
	
}
