package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.YlfwYyghDiseaseDao;
import com.lcworld.entity.YlfwYyghDiseaseEntity;
import com.lcworld.service.YlfwYyghDiseaseService;



@Service("ylfwYyghDiseaseService")
public class YlfwYyghDiseaseServiceImpl implements YlfwYyghDiseaseService {
	@Autowired
	private YlfwYyghDiseaseDao ylfwYyghDiseaseDao;
	
	@Override
	public YlfwYyghDiseaseEntity queryObject(Integer id){
		return ylfwYyghDiseaseDao.queryObject(id);
	}
	
	@Override
	public List<YlfwYyghDiseaseEntity> queryList(Map<String, Object> map){
		return ylfwYyghDiseaseDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwYyghDiseaseDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwYyghDiseaseEntity ylfwYyghDisease){
		ylfwYyghDiseaseDao.save(ylfwYyghDisease);
	}
	
	@Override
	public void update(YlfwYyghDiseaseEntity ylfwYyghDisease){
		ylfwYyghDiseaseDao.update(ylfwYyghDisease);
	}
	
	@Override
	public void delete(Integer id){
		ylfwYyghDiseaseDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwYyghDiseaseDao.deleteBatch(ids);
	}
	
}
