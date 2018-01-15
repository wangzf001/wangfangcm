package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TYytcStepDao;
import com.lcworld.entity.TYytcStepEntity;
import com.lcworld.service.TYytcStepService;



@Service("tYytcStepService")
public class TYytcStepServiceImpl implements TYytcStepService {
	@Autowired
	private TYytcStepDao tYytcStepDao;
	
	@Override
	public TYytcStepEntity queryObject(Integer sId){
		return tYytcStepDao.queryObject(sId);
	}
	
	@Override
	public List<TYytcStepEntity> queryList(Map<String, Object> map){
		return tYytcStepDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tYytcStepDao.queryTotal(map);
	}
	
	@Override
	public void save(TYytcStepEntity tYytcStep){
		tYytcStepDao.save(tYytcStep);
	}
	
	@Override
	public void update(TYytcStepEntity tYytcStep){
		tYytcStepDao.update(tYytcStep);
	}
	
	@Override
	public void delete(Integer sId){
		tYytcStepDao.delete(sId);
	}
	
	@Override
	public void deleteBatch(Integer[] sIds){
		tYytcStepDao.deleteBatch(sIds);
	}
	
}
