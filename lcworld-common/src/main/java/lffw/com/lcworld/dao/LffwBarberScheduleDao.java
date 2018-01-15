package com.lcworld.dao;

import java.util.List;

import com.lcworld.dto.LffwBarberScheduleEntityDTO;
import com.lcworld.entity.LffwBarberScheduleEntity;
import com.lcworld.utils.Query;

/**
 * 理发服务- 理发师计划
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
public interface LffwBarberScheduleDao extends BaseDao<LffwBarberScheduleEntity> {

    List<LffwBarberScheduleEntityDTO> queryScheduleList(Query query);

    int queryScheduleTotal(Query query);
	
}
