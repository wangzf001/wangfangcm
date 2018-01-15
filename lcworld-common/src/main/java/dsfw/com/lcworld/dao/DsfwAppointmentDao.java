package com.lcworld.dao;

import com.lcworld.entity.DsfwAppointmentEntity;
import com.lcworld.vo.AppointmentVo;

import java.util.List;
import java.util.Map;

/**
 * 订水服务预约时间表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-22 11:17:30
 */
public interface DsfwAppointmentDao extends BaseDao<DsfwAppointmentEntity> {

    List<AppointmentVo> findAvailableTime(Map<String,Object> params);

    void clearAppointment(Integer id);
}
