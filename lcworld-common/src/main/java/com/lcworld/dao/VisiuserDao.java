package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.VisiuserEntity;
import com.lcworld.utils.Query;

/**
 * 来访人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-07 14:49:00
 */
public interface VisiuserDao extends BaseDao<VisiuserEntity> {

    void checkBatch(Integer[] ids);

    void uncheckBatch(JSONObject obj);

	List<Map<String,Object>> queryOrderList(Query q);

	List<Map<String,Object>> queryvuserList(Query query);
	
	VisiuserEntity queryObject(Integer id);

	VisiuserEntity queryObjectByOid(Integer id);

	VisiuserEntity queryVisitLogById(Integer id);
}
