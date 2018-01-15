package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.YlfwYyghDoctorScheduleDao;
import com.lcworld.dto.DoctorScheduleDTO;
import com.lcworld.entity.YlfwYyghDoctorScheduleEntity;
import com.lcworld.service.YlfwYyghDoctorScheduleService;



@Service("ylfwYyghDoctorScheduleService")
public class YlfwYyghDoctorScheduleServiceImpl implements YlfwYyghDoctorScheduleService {
	@Autowired
	private YlfwYyghDoctorScheduleDao ylfwYyghDoctorScheduleDao;
	
	@Override
	public YlfwYyghDoctorScheduleEntity queryObject(Integer id){
		return ylfwYyghDoctorScheduleDao.queryObject(id);
	}
	
	@Override
	public List<YlfwYyghDoctorScheduleEntity> queryList(Map<String, Object> map){
		return ylfwYyghDoctorScheduleDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ylfwYyghDoctorScheduleDao.queryTotal(map);
	}
	
	@Override
	public void save(YlfwYyghDoctorScheduleEntity ylfwYyghDoctorSchedule){
		ylfwYyghDoctorScheduleDao.save(ylfwYyghDoctorSchedule);
	}
	
	@Override
	public void update(YlfwYyghDoctorScheduleEntity ylfwYyghDoctorSchedule){
		ylfwYyghDoctorScheduleDao.update(ylfwYyghDoctorSchedule);
	}
	
	@Override
	public void delete(Integer id){
		ylfwYyghDoctorScheduleDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		ylfwYyghDoctorScheduleDao.deleteBatch(ids);
	}
	
	 @Override
    public List<DoctorScheduleDTO> queryDocSchedules(JSONObject params) {
        return ylfwYyghDoctorScheduleDao.queryDocSchedules(params);
    }
	    
	
}
