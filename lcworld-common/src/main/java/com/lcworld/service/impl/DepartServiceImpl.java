package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.DepartDao;
import com.lcworld.entity.DepartEntity;
import com.lcworld.service.DepartService;



@Service("departService")
public class DepartServiceImpl implements DepartService {
	@Autowired
	private DepartDao departDao;
	
	@Override
	public DepartEntity queryObject(Integer id){
		return departDao.queryObject(id);
	}
	
	@Override
	public List<DepartEntity> queryList(Map<String, Object> map){
		return departDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return departDao.queryTotal(map);
	}
	
	@Override
	public void save(DepartEntity depart){
		departDao.save(depart);
	}
	
	@Override
	public void update(DepartEntity depart){
		departDao.update(depart);
	}
	
	@Override
	public void delete(Integer id){
		departDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		departDao.deleteBatch(ids);
	}
	
}
