package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TdhdActivityDemandDao;
import com.lcworld.entity.TdhdActivityDemandEntity;
import com.lcworld.service.TdhdActivityDemandService;



@Service("tdhqActivityDemandService")
public class TdhdActivityDemandServiceImpl implements TdhdActivityDemandService {
	@Autowired
	private TdhdActivityDemandDao tdhdActivityDemandDao;
	
	@Override
	public TdhdActivityDemandEntity queryObject(Integer id){
		return tdhdActivityDemandDao.queryObject(id);
	}
	
	@Override
	public List<TdhdActivityDemandEntity> queryList(Map<String, Object> map){
		return tdhdActivityDemandDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tdhdActivityDemandDao.queryTotal(map);
	}
	
	@Override
	public void save(TdhdActivityDemandEntity tdhqActivityDemand){
		tdhdActivityDemandDao.save(tdhqActivityDemand);
	}
	
	@Override
	public void update(TdhdActivityDemandEntity tdhqActivityDemand){
		tdhdActivityDemandDao.update(tdhqActivityDemand);
	}
	
	@Override
	public void delete(Integer id){
		tdhdActivityDemandDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tdhdActivityDemandDao.deleteBatch(ids);
	}
	
}
