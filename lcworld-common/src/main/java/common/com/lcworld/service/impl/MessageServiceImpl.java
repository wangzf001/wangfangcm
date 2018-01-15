package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.MessageDao;
import com.lcworld.entity.MessageEntity;
import com.lcworld.service.MessageService;



@Service("messageService")
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;
	
	@Override
	public MessageEntity queryObject(Integer id){
		return messageDao.queryObject(id);
	}
	
	@Override
	public List<MessageEntity> queryList(Map<String, Object> map){
		return messageDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return messageDao.queryTotal(map);
	}
	
	@Override
	public void save(MessageEntity message){
		messageDao.save(message);
	}
	
	@Override
	public void update(MessageEntity message){
		messageDao.update(message);
	}
	
	@Override
	public void delete(Integer id){
		messageDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		messageDao.deleteBatch(ids);
	}

	@Override
	public List<MessageEntity> queryMessagePage(Map<String,Object> params) {
		return messageDao.queryMessagePage(params);
	}

	@Override
	public MessageEntity queryMessageDetail(Map<String,Object> params) {
		// TODO Auto-generated method stub
		return messageDao.queryMessageDetail(params);
	}

	@Override
	public void updateReadStatus(Map<String,Object> params) {
		messageDao.updateReadStatus(params);
	}

	@Override
	public List<MessageEntity> queryListByInfo(JSONObject p) {
		
		return messageDao.queryListByInfo(p);
	}
	
}
