package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.JyfwFeedbackMsgEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 16:01:18
 */
public interface JyfwFeedbackMsgService extends TalkService{
	
	JyfwFeedbackMsgEntity queryObject(Integer id);
	
	List<JyfwFeedbackMsgEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(JyfwFeedbackMsgEntity jyfwFeedbackMsg);
	
	void update(JyfwFeedbackMsgEntity jyfwFeedbackMsg);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 将所有list中的反馈设置为已读
	 * @param msgList
	 */
	void updateListRead(List<JyfwFeedbackMsgEntity> msgList);
	/**
	 * 查询已经反馈的服务端id
	 * @param params
	 * @return
	 */
	Integer queryServiceUid(Map<String,Object> params);
}
