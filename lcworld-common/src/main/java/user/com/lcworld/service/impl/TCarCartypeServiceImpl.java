package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TCarCartypeDao;
import com.lcworld.entity.TCarCartypeEntity;
import com.lcworld.service.TCarCartypeService;



@Service("tCarCartypeService")
public class TCarCartypeServiceImpl implements TCarCartypeService {
	@Autowired
	private TCarCartypeDao tCarCartypeDao;
	
	@Override
	public TCarCartypeEntity queryObject(Integer id){
		return tCarCartypeDao.queryObject(id);
	}
	
	@Override
	public List<TCarCartypeEntity> queryList(Map<String, Object> map){
		return tCarCartypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tCarCartypeDao.queryTotal(map);
	}
	
	@Override
	public void save(TCarCartypeEntity tCarCartype){
		tCarCartypeDao.save(tCarCartype);
	}
	
	@Override
	public void update(TCarCartypeEntity tCarCartype){
		tCarCartypeDao.update(tCarCartype);
	}
	
	@Override
	public void delete(Integer id){
		tCarCartypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tCarCartypeDao.deleteBatch(ids);
	}
	
}
