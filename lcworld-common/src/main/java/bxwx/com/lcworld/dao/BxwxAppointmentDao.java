package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.BxwxAppointmentEntity;
import com.lcworld.vo.BxwxAppointmentVo;

/**
 * 报修维修时间段表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-07 17:28:31
 */
public interface BxwxAppointmentDao extends BaseDao<BxwxAppointmentEntity> {
	List<BxwxAppointmentVo> findAvailableTime(Map<String,Object> params);

	void clearAppointment(Integer id);
}
