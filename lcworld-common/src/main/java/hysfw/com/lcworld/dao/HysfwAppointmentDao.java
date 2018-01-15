package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.HysfwAppointmentEntity;
import com.lcworld.vo.HysfwAppointmentVo;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
public interface HysfwAppointmentDao extends BaseDao<HysfwAppointmentEntity> {

	List<HysfwAppointmentVo> findAvailableTime(Map<String,Object> params);

	void clearAppointment(Integer id);
	
}
