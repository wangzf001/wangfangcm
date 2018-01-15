package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TVisitUserEntity;
import com.lcworld.entity.VisiuserEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 来访人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-07 14:49:00
 */
public interface VisiuserService {
	
	VisiuserEntity queryObject(Integer id);

	VisiuserEntity  queryObjectByOid(Integer id);

	VisiuserEntity queryVisitLogById(Integer id);

	List<VisiuserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(VisiuserEntity visiuser);
	
	void update(VisiuserEntity visiuser);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void checkBatch(Integer[] ids);

    void uncheckBatch(JSONObject obj);

	List<Map<String,Object>> queryOrderList(Query q);

	void updateVisitUserlog(VisiuserEntity visitUser);

	List<Map<String,Object>> queryvuserList(Query query);

	void savevisituser(VisiuserEntity visitUser);
}
