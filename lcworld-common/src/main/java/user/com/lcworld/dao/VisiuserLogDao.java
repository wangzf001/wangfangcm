package com.lcworld.dao;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.VisiuserLogEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-20 12:02:47
 */
public interface VisiuserLogDao extends BaseDao<VisiuserLogEntity> {

	void uncheckBatchbyuvids(JSONObject obj);

	VisiuserLogEntity queryObjectByCode(String code);
}
