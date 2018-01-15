package com.lcworld.dao;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.FwCommenviewEntity;

/**
 * VIEW
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-05 17:28:34
 */
public interface FwCommenviewDao extends BaseDao<FwCommenviewEntity> {
	
	FwCommenviewEntity queryGoodComment(JSONObject params);
}
