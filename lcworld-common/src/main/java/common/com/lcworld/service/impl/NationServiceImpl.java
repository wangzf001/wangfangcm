package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.NationDao;
import com.lcworld.entity.NationEntity;
import com.lcworld.service.NationService;



@Service("nationService")
public class NationServiceImpl implements NationService {
	@Autowired
	private NationDao nationDao;
	
	@Override
	public NationEntity queryObject(Integer id){
		return nationDao.queryObject(id);
	}
	
	@Override
	public List<NationEntity> queryList(Map<String, Object> map){
		return nationDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return nationDao.queryTotal(map);
	}
	
	@Override
	public void save(NationEntity nation){
		nationDao.save(nation);
	}
	
	@Override
	public void update(NationEntity nation){
		nationDao.update(nation);
	}
	
	@Override
	public void delete(Integer id){
		nationDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		nationDao.deleteBatch(ids);
	}
	
}
