package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.FwOrderCancelReasonEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-21 16:24:53
 */
public interface FwOrderCancelReasonDao extends BaseDao<FwOrderCancelReasonEntity> {

	List<Map<String, Object>> getCancelOrderReason(JSONObject p);
	
}
