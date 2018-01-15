package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TDcfwOrderdetailDao;
import com.lcworld.entity.TDcfwOrderdetailEntity;
import com.lcworld.service.TDcfwOrderdetailService;



@Service("tDcfwOrderdetailService")
public class TDcfwOrderdetailServiceImpl implements TDcfwOrderdetailService {
	@Autowired
	private TDcfwOrderdetailDao tDcfwOrderdetailDao;
	
	@Override
	public TDcfwOrderdetailEntity queryObject(Integer id){
		return tDcfwOrderdetailDao.queryObject(id);
	}
	
	@Override
	public List<TDcfwOrderdetailEntity> queryList(Map<String, Object> map){
		return tDcfwOrderdetailDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tDcfwOrderdetailDao.queryTotal(map);
	}
	
	@Override
	public void save(TDcfwOrderdetailEntity tDcfwOrderdetail){
		tDcfwOrderdetailDao.save(tDcfwOrderdetail);
	}
	
	@Override
	public void update(TDcfwOrderdetailEntity tDcfwOrderdetail){
		tDcfwOrderdetailDao.update(tDcfwOrderdetail);
	}
	
	@Override
	public void delete(Integer id){
		tDcfwOrderdetailDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tDcfwOrderdetailDao.deleteBatch(ids);
	}
	
}
