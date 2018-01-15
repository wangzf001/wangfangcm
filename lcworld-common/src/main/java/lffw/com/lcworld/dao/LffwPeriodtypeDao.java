package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.LffwscheduleDTO;
import com.lcworld.entity.LffwPeriodtypeEntity;

/**
 * 理发服务--时间段类型
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 19:30:54
 */
public interface LffwPeriodtypeDao extends BaseDao<LffwPeriodtypeEntity> {

    List<LffwscheduleDTO> querytimelist(JSONObject params);
	
}
