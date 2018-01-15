package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.FwCommenviewDao;
import com.lcworld.entity.FwCommenviewEntity;
import com.lcworld.service.FwCommenviewService;



@Service("fwCommenviewService")
public class FwCommenviewServiceImpl implements FwCommenviewService {
	@Autowired
	private FwCommenviewDao fwCommenviewDao;
	
	@Override
	public FwCommenviewEntity queryObject(String id){
		return fwCommenviewDao.queryObject(id);
	}
	
	@Override
	public List<FwCommenviewEntity> queryList(Map<String, Object> map){
		return fwCommenviewDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return fwCommenviewDao.queryTotal(map);
	}
	
	@Override
	public void save(FwCommenviewEntity fwCommenview){
		fwCommenviewDao.save(fwCommenview);
	}
	
	@Override
	public void update(FwCommenviewEntity fwCommenview){
		fwCommenviewDao.update(fwCommenview);
	}
	
	@Override
	public void delete(String id){
		fwCommenviewDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		fwCommenviewDao.deleteBatch(ids);
	}

	@Override
	public FwCommenviewEntity queryGoodComment(JSONObject params) {
		// TODO Auto-generated method stub
		return fwCommenviewDao.queryGoodComment(params);
	}
	
}
