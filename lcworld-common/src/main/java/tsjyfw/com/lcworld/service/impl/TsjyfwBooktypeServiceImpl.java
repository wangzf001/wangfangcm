package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TsjyfwBooktypeDao;
import com.lcworld.entity.TsjyfwBooktypeEntity;
import com.lcworld.service.TsjyfwBooktypeService;



@Service("tsjyfwBooktypeService")
public class TsjyfwBooktypeServiceImpl implements TsjyfwBooktypeService {
	@Autowired
	private TsjyfwBooktypeDao tsjyfwBooktypeDao;
	
	@Override
	public TsjyfwBooktypeEntity queryObject(Integer id){
		return tsjyfwBooktypeDao.queryObject(id);
	}
	
	@Override
	public List<TsjyfwBooktypeEntity> queryList(Map<String, Object> map){
		return tsjyfwBooktypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tsjyfwBooktypeDao.queryTotal(map);
	}
	
	@Override
	public void save(TsjyfwBooktypeEntity tsjyfwBooktype){
		tsjyfwBooktypeDao.save(tsjyfwBooktype);
	}
	
	@Override
	public void update(TsjyfwBooktypeEntity tsjyfwBooktype){
		tsjyfwBooktypeDao.update(tsjyfwBooktype);
	}
	
	@Override
	public void delete(Integer id){
		tsjyfwBooktypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tsjyfwBooktypeDao.deleteBatch(ids);
	}
	
}
