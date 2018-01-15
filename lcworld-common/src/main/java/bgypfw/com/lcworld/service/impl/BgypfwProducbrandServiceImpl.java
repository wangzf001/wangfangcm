package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BgypfwProducbrandDao;
import com.lcworld.entity.BgypfwProducbrandEntity;
import com.lcworld.service.BgypfwProducbrandService;



@Service("bgypfwProducbrandService")
public class BgypfwProducbrandServiceImpl implements BgypfwProducbrandService {
	@Autowired
	private BgypfwProducbrandDao bgypfwProducbrandDao;
	
	@Override
	public BgypfwProducbrandEntity queryObject(Integer id){
		return bgypfwProducbrandDao.queryObject(id);
	}
	
	@Override
	public List<BgypfwProducbrandEntity> queryList(Map<String, Object> map){
		return bgypfwProducbrandDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwProducbrandDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwProducbrandEntity bgypfwProducbrand){
		bgypfwProducbrandDao.save(bgypfwProducbrand);
	}
	
	@Override
	public void update(BgypfwProducbrandEntity bgypfwProducbrand){
		bgypfwProducbrandDao.update(bgypfwProducbrand);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwProducbrandDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwProducbrandDao.deleteBatch(ids);
	}
	
}
