package com.lcworld.service;

import com.lcworld.dto.LffwBarberScheduleEntityDTO;
import com.lcworld.entity.LffwBarberScheduleEntity;
import com.lcworld.utils.Query;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * 理发服务- 理发师计划
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
public interface LffwBarberScheduleService {
	
	LffwBarberScheduleEntity queryObject(Integer id);
	
	List<LffwBarberScheduleEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwBarberScheduleEntity lffwBarberSchedule);
	
	void update(LffwBarberScheduleEntity lffwBarberSchedule);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<LffwBarberScheduleEntityDTO> queryScheduleList(Query query);

    int queryScheduleTotal(Query query);

    void saveSchedule(LffwBarberScheduleEntity lffwBarberSchedule);

    void updateSchedule(LffwBarberScheduleEntity lffwBarberSchedule);

    void saveschedule(LffwBarberScheduleEntity lffwBarberSchedule) throws ParseException;
}
