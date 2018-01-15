package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.OfficeDao;
import com.lcworld.entity.OfficeEntity;
import com.lcworld.service.OfficeService;



@Service("officeService")
public class OfficeServiceImpl implements OfficeService {
	@Autowired
	private OfficeDao officeDao;
	
	@Override
	public OfficeEntity queryObject(Integer id){
		return officeDao.queryObject(id);
	}
	
	@Override
	public List<OfficeEntity> queryList(Map<String, Object> map){
		return officeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return officeDao.queryTotal(map);
	}
	
	@Override
	public void save(OfficeEntity office){
		officeDao.save(office);
	}
	
	@Override
	public void update(OfficeEntity office){
		officeDao.update(office);
	}
	
	@Override
	public void delete(Integer id){
		officeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		officeDao.deleteBatch(ids);
	}
	
}
