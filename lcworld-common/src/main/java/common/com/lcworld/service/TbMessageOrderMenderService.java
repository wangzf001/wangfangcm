package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TbMessageOrderMenderEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-26 14:24:46
 */
public interface TbMessageOrderMenderService {
	
	TbMessageOrderMenderEntity queryObject(Long autoId);
	
	List<TbMessageOrderMenderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TbMessageOrderMenderEntity tbMessageOrderMender);
	
	void update(TbMessageOrderMenderEntity tbMessageOrderMender);
	
	void delete(Long autoId);
	
	void deleteBatch(Long[] autoIds);
	
	List<TbMessageOrderMenderEntity> queryListByInfo(JSONObject p);
	
	int unReadMessageCount(JSONObject p);
}
