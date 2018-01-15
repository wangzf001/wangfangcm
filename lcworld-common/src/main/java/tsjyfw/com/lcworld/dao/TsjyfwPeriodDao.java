package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TsjyfwPeriodEntity;

/**
 * 图书借阅服务-时间段
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
public interface TsjyfwPeriodDao extends BaseDao<TsjyfwPeriodEntity> {

    List<TsjyfwPeriodEntity> querytimeList(JSONObject map);
	
}
