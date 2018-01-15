package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.DsfwSendtimeDao;
import com.lcworld.entity.DsfwSendtimeEntity;
import com.lcworld.service.DsfwSendtimeService;



@Service("dsfwSendtimeService")
public class DsfwSendtimeServiceImpl implements DsfwSendtimeService {
	@Autowired
	private DsfwSendtimeDao dsfwSendtimeDao;
	
	@Override
	public DsfwSendtimeEntity queryObject(Integer id){
		return dsfwSendtimeDao.queryObject(id);
	}
	
	@Override
	public List<DsfwSendtimeEntity> queryList(Map<String, Object> map){
		return dsfwSendtimeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dsfwSendtimeDao.queryTotal(map);
	}
	
	@Override
	public void save(DsfwSendtimeEntity dsfwSendtime){
		dsfwSendtimeDao.save(dsfwSendtime);
	}
	
	@Override
	public void update(DsfwSendtimeEntity dsfwSendtime){
		dsfwSendtimeDao.update(dsfwSendtime);
	}
	
	@Override
	public void delete(Integer id){
		dsfwSendtimeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dsfwSendtimeDao.deleteBatch(ids);
	}
	
}
