package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.DoctorDTO;
import com.lcworld.dto.DoctorScheduleDTO;
import com.lcworld.entity.YlfwYyghDoctorEntity;
import com.lcworld.entity.YlfwYyghDoctorScheduleEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 医疗服务医生表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-20 15:02:04
 */
public interface YlfwYyghDoctorService {
	
	YlfwYyghDoctorEntity queryObject(Integer id);
	
	List<YlfwYyghDoctorEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwYyghDoctorEntity ylfwYyghDoctor);
	
	void update(YlfwYyghDoctorEntity ylfwYyghDoctor);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<DoctorDTO> queryDoctorList(Query q);

}
