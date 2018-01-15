package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.YlfwHospitalDao;
import com.lcworld.entity.YlfwHospitalEntity;
import com.lcworld.service.YlfwHospitalService;



@Service("ylfwHospitalService")
public class YlfwHospitalServiceImpl implements YlfwHospitalService {
	@Autowired
	private YlfwHospitalDao ylfwHospitalDao;
	
	@Override
	public YlfwHospitalEntity queryObject(Integer id){
		return ylfwHospitalDao.queryObject(id);
	}
	
	@Override
	public List<YlfwHospitalEntity> queryList(Map<String, Object> map){
		return ylfwHospitalDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwHospitalDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwHospitalEntity ylfwHospital){
		ylfwHospitalDao.save(ylfwHospital);
	}
	
	@Override
	public void update(YlfwHospitalEntity ylfwHospital){
		ylfwHospitalDao.update(ylfwHospital);
	}
	
	@Override
	public void delete(Integer id){
		ylfwHospitalDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwHospitalDao.deleteBatch(ids);
	}
	
}
