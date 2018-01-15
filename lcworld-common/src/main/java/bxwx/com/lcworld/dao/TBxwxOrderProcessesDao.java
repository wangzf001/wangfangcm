package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.BxwxOrderProcessDTO;
import com.lcworld.entity.TBxwxOrderProcessesEntity;

/**
 * 订单流程表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
public interface TBxwxOrderProcessesDao extends BaseDao<TBxwxOrderProcessesEntity> {

    List<BxwxOrderProcessDTO> queryProcessList(JSONObject params);
	
}
