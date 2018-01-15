package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.YlfwPositionDao;
import com.lcworld.entity.YlfwPositionEntity;
import com.lcworld.service.YlfwPositionService;



@Service("ylfwPositionService")
public class YlfwPositionServiceImpl implements YlfwPositionService {
	@Autowired
	private YlfwPositionDao ylfwPositionDao;
	
	@Override
	public YlfwPositionEntity queryObject(Integer id){
		return ylfwPositionDao.queryObject(id);
	}
	
	@Override
	public List<YlfwPositionEntity> queryList(Map<String, Object> map){
		return ylfwPositionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwPositionDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwPositionEntity ylfwPosition){
		ylfwPositionDao.save(ylfwPosition);
	}
	
	@Override
	public void update(YlfwPositionEntity ylfwPosition){
		ylfwPositionDao.update(ylfwPosition);
	}
	
	@Override
	public void delete(Integer id){
		ylfwPositionDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwPositionDao.deleteBatch(ids);
	}
	
}
