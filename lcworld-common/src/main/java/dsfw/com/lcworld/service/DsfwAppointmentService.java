package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.DsfwAppointmentEntity;
import com.lcworld.vo.AppointmentResultVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 订水服务预约时间表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-22 11:17:30
 */
public interface DsfwAppointmentService {
	
	DsfwAppointmentEntity queryObject(Integer id);
	
	List<DsfwAppointmentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DsfwAppointmentEntity dsfwAppointment);
	
	void update(DsfwAppointmentEntity dsfwAppointment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	/**
	 * 查询可预约的日期
	 * @param params
	 * @return
	 */
	Set<AppointmentResultVo> findAvailableTime(JSONObject params);
}
