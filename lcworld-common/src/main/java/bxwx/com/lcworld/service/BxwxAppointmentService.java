package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.BxwxAppointmentEntity;
import com.lcworld.vo.BxwxAppointmentResultVo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 报修维修时间段表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-07 17:28:31
 */
public interface BxwxAppointmentService {
	
	BxwxAppointmentEntity queryObject(Integer id);
	
	List<BxwxAppointmentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BxwxAppointmentEntity bxwxAppointment);
	
	void update(BxwxAppointmentEntity bxwxAppointment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	/**
	 * 查询可预约的日期
	 * @param date
	 * @return
	 */
	Set<BxwxAppointmentResultVo> findAvailableTime(JSONObject params);
}
