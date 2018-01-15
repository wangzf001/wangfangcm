package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.InneruserDao;
import com.lcworld.entity.InneruserEntity;
import com.lcworld.service.InneruserService;



@Service("inneruserService")
public class InneruserServiceImpl implements InneruserService {
	@Autowired
	private InneruserDao inneruserDao;
	
	@Override
	public InneruserEntity queryObject(Integer id){
		return inneruserDao.queryObject(id);
	}
	
	@Override
	public List<InneruserEntity> queryList(Map<String, Object> map){
		return inneruserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return inneruserDao.queryTotal(map);
	}
	
	@Override
	public void save(InneruserEntity inneruser){
		inneruserDao.save(inneruser);
	}
	
	@Override
	public void update(InneruserEntity inneruser){
		inneruserDao.update(inneruser);
	}
	
	@Override
	public void delete(Integer id){
		inneruserDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		inneruserDao.deleteBatch(ids);
	}
	
}
