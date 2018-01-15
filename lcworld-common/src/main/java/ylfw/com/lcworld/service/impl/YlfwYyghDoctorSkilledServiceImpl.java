package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.YlfwYyghDoctorSkilledDao;
import com.lcworld.entity.YlfwYyghDoctorSkilledEntity;
import com.lcworld.service.YlfwYyghDoctorSkilledService;



@Service("ylfwYyghDoctorSkilledService")
public class YlfwYyghDoctorSkilledServiceImpl implements YlfwYyghDoctorSkilledService {
	@Autowired
	private YlfwYyghDoctorSkilledDao ylfwYyghDoctorSkilledDao;
	
	@Override
	public YlfwYyghDoctorSkilledEntity queryObject(Integer id){
		return ylfwYyghDoctorSkilledDao.queryObject(id);
	}
	
	@Override
	public List<YlfwYyghDoctorSkilledEntity> queryList(Map<String, Object> map){
		return ylfwYyghDoctorSkilledDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwYyghDoctorSkilledDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwYyghDoctorSkilledEntity ylfwYyghDoctorSkilled){
		ylfwYyghDoctorSkilledDao.save(ylfwYyghDoctorSkilled);
	}
	
	@Override
	public void update(YlfwYyghDoctorSkilledEntity ylfwYyghDoctorSkilled){
		ylfwYyghDoctorSkilledDao.update(ylfwYyghDoctorSkilled);
	}
	
	@Override
	public void delete(Integer id){
		ylfwYyghDoctorSkilledDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwYyghDoctorSkilledDao.deleteBatch(ids);
	}
	
}
