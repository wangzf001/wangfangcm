package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.MessageEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-20 14:54:20
 */
public interface MessageDao extends BaseDao<MessageEntity> {

	List<MessageEntity> queryMessagePage(Map<String, Object> params);

	MessageEntity queryMessageDetail(Map<String, Object> params);

	void updateReadStatus(Map<String, Object> params);
	
	List<MessageEntity> queryListByInfo(JSONObject p);
	
}
