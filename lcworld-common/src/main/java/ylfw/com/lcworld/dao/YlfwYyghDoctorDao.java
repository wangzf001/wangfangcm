package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.DoctorDTO;
import com.lcworld.dto.DoctorScheduleDTO;
import com.lcworld.entity.YlfwYyghDoctorEntity;
import com.lcworld.utils.Query;

/**
 * 医疗服务医生表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-20 15:02:04
 */
public interface YlfwYyghDoctorDao extends BaseDao<YlfwYyghDoctorEntity> {

    List<DoctorDTO> queryDoctorList(Query q);
	
}
