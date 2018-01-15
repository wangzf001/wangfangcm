package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.VisiuserLogDao;
import com.lcworld.entity.VisiuserLogEntity;
import com.lcworld.service.VisiuserLogService;



@Service("visiuserLogService")
public class VisiuserLogServiceImpl implements VisiuserLogService {
	@Autowired
	private VisiuserLogDao visiuserLogDao;
	
	@Override
	public VisiuserLogEntity queryObject(Integer id){
		return visiuserLogDao.queryObject(id);
	}

	@Override
	public VisiuserLogEntity queryObjectByCode(String code) {
		return visiuserLogDao.queryObjectByCode(code);
	}

	@Override
	public List<VisiuserLogEntity> queryList(Map<String, Object> map){
		return visiuserLogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return visiuserLogDao.queryTotal(map);
	}
	
	@Override
	public void save(VisiuserLogEntity visiuserLog){
		visiuserLogDao.save(visiuserLog);
	}
	
	@Override
	public void update(VisiuserLogEntity visiuserLog){
		visiuserLogDao.update(visiuserLog);
	}
	
	@Override
	public void delete(Integer id){
		visiuserLogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		visiuserLogDao.deleteBatch(ids);
	}

	@Override
	public void uncheckBatchbyuvids(JSONObject obj) {
		visiuserLogDao.uncheckBatchbyuvids(obj);
		
	}
	
}
