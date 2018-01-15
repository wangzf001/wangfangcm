package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.VisiuserLogEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-20 12:02:47
 */
public interface VisiuserLogService {
	
	VisiuserLogEntity queryObject(Integer id);

	VisiuserLogEntity queryObjectByCode(String code);

	List<VisiuserLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(VisiuserLogEntity visiuserLog);
	
	void update(VisiuserLogEntity visiuserLog);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void uncheckBatchbyuvids(JSONObject obj);

}
