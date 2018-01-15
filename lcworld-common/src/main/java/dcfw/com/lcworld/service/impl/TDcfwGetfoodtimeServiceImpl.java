package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TDcfwGetfoodtimeDao;
import com.lcworld.entity.TDcfwGetfoodtimeEntity;
import com.lcworld.service.TDcfwGetfoodtimeService;



@Service("tDcfwGetfoodtimeService")
public class TDcfwGetfoodtimeServiceImpl implements TDcfwGetfoodtimeService {
	@Autowired
	private TDcfwGetfoodtimeDao tDcfwGetfoodtimeDao;
	
	@Override
	public TDcfwGetfoodtimeEntity queryObject(Integer id){
		return tDcfwGetfoodtimeDao.queryObject(id);
	}
	
	@Override
	public List<TDcfwGetfoodtimeEntity> queryList(Map<String, Object> map){
		return tDcfwGetfoodtimeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tDcfwGetfoodtimeDao.queryTotal(map);
	}
	
	@Override
	public void save(TDcfwGetfoodtimeEntity tDcfwGetfoodtime){
		tDcfwGetfoodtimeDao.save(tDcfwGetfoodtime);
	}
	
	@Override
	public void update(TDcfwGetfoodtimeEntity tDcfwGetfoodtime){
		tDcfwGetfoodtimeDao.update(tDcfwGetfoodtime);
	}
	
	@Override
	public void delete(Integer id){
		tDcfwGetfoodtimeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tDcfwGetfoodtimeDao.deleteBatch(ids);
	}
	
}
