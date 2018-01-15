package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BgypfwOrderdetailDao;
import com.lcworld.entity.BgypfwOrderdetailEntity;
import com.lcworld.service.BgypfwOrderdetailService;



@Service("bgypfwOrderdetailService")
public class BgypfwOrderdetailServiceImpl implements BgypfwOrderdetailService {
	@Autowired
	private BgypfwOrderdetailDao bgypfwOrderdetailDao;
	
	@Override
	public BgypfwOrderdetailEntity queryObject(Integer id){
		return bgypfwOrderdetailDao.queryObject(id);
	}
	
	@Override
	public List<BgypfwOrderdetailEntity> queryList(Map<String, Object> map){
		return bgypfwOrderdetailDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwOrderdetailDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwOrderdetailEntity bgypfwOrderdetail){
		bgypfwOrderdetailDao.save(bgypfwOrderdetail);
	}
	
	@Override
	public void update(BgypfwOrderdetailEntity bgypfwOrderdetail){
		bgypfwOrderdetailDao.update(bgypfwOrderdetail);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwOrderdetailDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwOrderdetailDao.deleteBatch(ids);
	}
	
}
