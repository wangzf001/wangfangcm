package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.DoctorScheduleDTO;
import com.lcworld.dto.ExpertScheduleDTO;
import com.lcworld.dto.YlfwZjzzScheduleEntityDTO;
import com.lcworld.entity.YlfwZjzzDoctorScheduleEntity;
import com.lcworld.utils.Query;

/**
 * 医疗服务专家坐诊—医生计划
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
public interface YlfwZjzzDoctorScheduleDao extends BaseDao<YlfwZjzzDoctorScheduleEntity> {

    List<ExpertScheduleDTO> queryDocSchedules(JSONObject params);

    List<YlfwZjzzScheduleEntityDTO> queryScheduleList(Query query);

    int queryScheduleTotal(Query query);
	
}
