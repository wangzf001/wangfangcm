package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.TbMessageOrderMenderDao;
import com.lcworld.entity.TbMessageOrderMenderEntity;
import com.lcworld.service.TbMessageOrderMenderService;



@Service("tbMessageOrderMenderService")
public class TbMessageOrderMenderServiceImpl implements TbMessageOrderMenderService {
	@Autowired
	private TbMessageOrderMenderDao tbMessageOrderMenderDao;
	
	@Override
	public TbMessageOrderMenderEntity queryObject(Long autoId){
		return tbMessageOrderMenderDao.queryObject(autoId);
	}
	
	@Override
	public List<TbMessageOrderMenderEntity> queryList(Map<String, Object> map){
		return tbMessageOrderMenderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tbMessageOrderMenderDao.queryTotal(map);
	}
	
	@Override
	public void save(TbMessageOrderMenderEntity tbMessageOrderMender){
		tbMessageOrderMenderDao.save(tbMessageOrderMender);
	}
	
	@Override
	public void update(TbMessageOrderMenderEntity tbMessageOrderMender){
		tbMessageOrderMenderDao.update(tbMessageOrderMender);
	}
	
	@Override
	public void delete(Long autoId){
		tbMessageOrderMenderDao.delete(autoId);
	}
	
	@Override
	public void deleteBatch(Long[] autoIds){
		tbMessageOrderMenderDao.deleteBatch(autoIds);
	}

	@Override
	public List<TbMessageOrderMenderEntity> queryListByInfo(JSONObject p) {
		
		return tbMessageOrderMenderDao.queryListByInfo(p);
	}

	@Override
	public int unReadMessageCount(JSONObject p) {
		
		return tbMessageOrderMenderDao.unReadMessageCount(p);
	}
	
}
