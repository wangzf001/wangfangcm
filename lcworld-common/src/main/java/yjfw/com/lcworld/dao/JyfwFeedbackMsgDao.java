package com.lcworld.dao;

import java.util.Map;

import com.lcworld.entity.JyfwFeedbackMsgEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 16:01:18
 */
public interface JyfwFeedbackMsgDao extends BaseDao<JyfwFeedbackMsgEntity> {

	void updateListRead(Integer[] array);

	Integer queryServiceUid(Map<String, Object> params);
	
}
