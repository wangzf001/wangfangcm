package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.WfAdInfoDao;
import com.lcworld.entity.WfAdInfoEntity;
import com.lcworld.service.WfAdInfoService;



@Service("wfAdInfoService")
public class WfAdInfoServiceImpl implements WfAdInfoService {
	@Autowired
	private WfAdInfoDao wfAdInfoDao;
	
	@Override
	public WfAdInfoEntity queryObject(Integer id){
		return wfAdInfoDao.queryObject(id);
	}
	
	@Override
	public List<WfAdInfoEntity> queryList(Map<String, Object> map){
		return wfAdInfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return wfAdInfoDao.queryTotal(map);
	}
	
	@Override
	public void save(WfAdInfoEntity wfAdInfo){
		wfAdInfoDao.save(wfAdInfo);
	}
	
	@Override
	public void update(WfAdInfoEntity wfAdInfo){
		wfAdInfoDao.update(wfAdInfo);
	}
	
	@Override
	public void delete(Integer id){
		wfAdInfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		wfAdInfoDao.deleteBatch(ids);
	}
	
}
