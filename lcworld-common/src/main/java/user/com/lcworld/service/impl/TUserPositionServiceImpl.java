package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TUserPositionDao;
import com.lcworld.entity.TUserPositionEntity;
import com.lcworld.service.TUserPositionService;



@Service("tUserPositionService")
public class TUserPositionServiceImpl implements TUserPositionService {
	@Autowired
	private TUserPositionDao tUserPositionDao;
	
	@Override
	public TUserPositionEntity queryObject(Integer id){
		return tUserPositionDao.queryObject(id);
	}
	
	@Override
	public List<TUserPositionEntity> queryList(Map<String, Object> map){
		return tUserPositionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tUserPositionDao.queryTotal(map);
	}
	
	@Override
	public void save(TUserPositionEntity tUserPosition){
		tUserPositionDao.save(tUserPosition);
	}
	
	@Override
	public void update(TUserPositionEntity tUserPosition){
		tUserPositionDao.update(tUserPosition);
	}
	
	@Override
	public void delete(Integer id){
		tUserPositionDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tUserPositionDao.deleteBatch(ids);
	}
	
}
