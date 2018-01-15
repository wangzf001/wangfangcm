package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.YlfwYyghDoctorDao;
import com.lcworld.dto.DoctorDTO;
import com.lcworld.dto.DoctorScheduleDTO;
import com.lcworld.entity.YlfwYyghDoctorEntity;
import com.lcworld.entity.YlfwYyghDoctorScheduleEntity;
import com.lcworld.service.YlfwYyghDoctorService;
import com.lcworld.utils.Query;



@Service("ylfwYyghDoctorService")
public class YlfwYyghDoctorServiceImpl implements YlfwYyghDoctorService {
	@Autowired
	private YlfwYyghDoctorDao ylfwYyghDoctorDao;
	
	@Override
	public YlfwYyghDoctorEntity queryObject(Integer id){
		return ylfwYyghDoctorDao.queryObject(id);
	}
	
	@Override
	public List<YlfwYyghDoctorEntity> queryList(Map<String, Object> map){
		return ylfwYyghDoctorDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwYyghDoctorDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwYyghDoctorEntity ylfwYyghDoctor){
		ylfwYyghDoctorDao.save(ylfwYyghDoctor);
	}
	
	@Override
	public void update(YlfwYyghDoctorEntity ylfwYyghDoctor){
		ylfwYyghDoctorDao.update(ylfwYyghDoctor);
	}
	
	@Override
	public void delete(Integer id){
		ylfwYyghDoctorDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwYyghDoctorDao.deleteBatch(ids);
	}


    @Override
    public List<DoctorDTO> queryDoctorList(Query q) {
       return ylfwYyghDoctorDao.queryDoctorList(q);
    }

   
}
