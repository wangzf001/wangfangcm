package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.MessageEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-20 14:54:20
 */
public interface MessageService {
	
	MessageEntity queryObject(Integer id);
	
	List<MessageEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MessageEntity message);
	
	void update(MessageEntity message);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 查询信息页
	 * @param params
	 * @return
	 */
	List<MessageEntity> queryMessagePage(Map<String, Object> params);
	/**
	 * 
	 * @param params
	 * @return
	 */
	MessageEntity queryMessageDetail(Map<String,Object> params);

	void updateReadStatus(Map<String,Object> params);
	
	List<MessageEntity> queryListByInfo(JSONObject p);
}
