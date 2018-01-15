package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.FwCommenviewEntity;

import java.util.List;
import java.util.Map;

/**
 * VIEW
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-05 17:28:34
 */
public interface FwCommenviewService {
	
	FwCommenviewEntity queryObject(String id);
	
	List<FwCommenviewEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(FwCommenviewEntity fwCommenview);
	
	void update(FwCommenviewEntity fwCommenview);
	
	void delete(String id);
	
	void deleteBatch(String[] ids);
	
	FwCommenviewEntity  queryGoodComment(JSONObject params);
}
