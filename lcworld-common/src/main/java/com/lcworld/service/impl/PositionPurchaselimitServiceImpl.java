package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.PositionPurchaselimitDao;
import com.lcworld.entity.PositionPurchaselimitEntity;
import com.lcworld.service.PositionPurchaselimitService;



@Service("positionPurchaselimitService")
public class PositionPurchaselimitServiceImpl implements PositionPurchaselimitService {
	@Autowired
	private PositionPurchaselimitDao positionPurchaselimitDao;
	
	@Override
	public PositionPurchaselimitEntity queryObject(Integer id){
		return positionPurchaselimitDao.queryObject(id);
	}
	
	@Override
	public List<PositionPurchaselimitEntity> queryList(Map<String, Object> map){
		return positionPurchaselimitDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return positionPurchaselimitDao.queryTotal(map);
	}
	
	@Override
	public void save(PositionPurchaselimitEntity positionPurchaselimit){
		positionPurchaselimitDao.save(positionPurchaselimit);
	}
	
	@Override
	public void update(PositionPurchaselimitEntity positionPurchaselimit){
		positionPurchaselimitDao.update(positionPurchaselimit);
	}
	
	@Override
	public void delete(Integer id){
		positionPurchaselimitDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		positionPurchaselimitDao.deleteBatch(ids);
	}
	
}
