package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TdhdActivityimgDao;
import com.lcworld.entity.TdhdActivityimgEntity;
import com.lcworld.service.TdhdActivityimgService;



@Service("tdhdActivityimgService")
public class TdhdActivityimgServiceImpl implements TdhdActivityimgService {
	@Autowired
	private TdhdActivityimgDao tdhdActivityimgDao;
	
	@Override
	public TdhdActivityimgEntity queryObject(Integer aiId){
		return tdhdActivityimgDao.queryObject(aiId);
	}
	
	@Override
	public List<TdhdActivityimgEntity> queryList(Map<String, Object> map){
		return tdhdActivityimgDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tdhdActivityimgDao.queryTotal(map);
	}
	
	@Override
	public void save(TdhdActivityimgEntity tdhdActivityimg){
		tdhdActivityimgDao.save(tdhdActivityimg);
	}
	
	@Override
	public void update(TdhdActivityimgEntity tdhdActivityimg){
		tdhdActivityimgDao.update(tdhdActivityimg);
	}
	
	@Override
	public void delete(Integer aiId){
		tdhdActivityimgDao.delete(aiId);
	}
	
	@Override
	public void deleteBatch(Integer[] aiIds){
		tdhdActivityimgDao.deleteBatch(aiIds);
	}
	
}
