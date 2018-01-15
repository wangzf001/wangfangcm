package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TNoticeSetDao;
import com.lcworld.entity.TNoticeSetEntity;
import com.lcworld.service.TNoticeSetService;



@Service("tNoticeSetService")
public class TNoticeSetServiceImpl implements TNoticeSetService {
	@Autowired
	private TNoticeSetDao tNoticeSetDao;
	
	@Override
	public TNoticeSetEntity queryObject(Integer id){
		return tNoticeSetDao.queryObject(id);
	}
	
	@Override
	public List<TNoticeSetEntity> queryList(Map<String, Object> map){
		return tNoticeSetDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tNoticeSetDao.queryTotal(map);
	}
	
	@Override
	public void save(TNoticeSetEntity tNoticeSet){
		tNoticeSetDao.save(tNoticeSet);
	}
	
	@Override
	public void update(TNoticeSetEntity tNoticeSet){
		tNoticeSetDao.update(tNoticeSet);
	}
	
	@Override
	public void delete(Integer id){
		tNoticeSetDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tNoticeSetDao.deleteBatch(ids);
	}
	
}
