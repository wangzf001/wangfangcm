package com.lcworld.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.HysfwAppointmentEntity;
import com.lcworld.vo.HysfwAppointmentResultVo;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
public interface HysfwAppointmentService {
	
	HysfwAppointmentEntity queryObject(Integer id);
	
	List<HysfwAppointmentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HysfwAppointmentEntity hysfwAppointment);
	
	void update(HysfwAppointmentEntity hysfwAppointment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 查询可预约的日期
	 * @param date
	 * @return
	 */
	Set<HysfwAppointmentResultVo> findAvailableTime(JSONObject params);
}
