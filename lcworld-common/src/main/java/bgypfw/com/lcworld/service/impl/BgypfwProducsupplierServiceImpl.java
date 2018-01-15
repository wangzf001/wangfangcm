package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BgypfwProducsupplierDao;
import com.lcworld.entity.BgypfwProducsupplierEntity;
import com.lcworld.service.BgypfwProducsupplierService;



@Service("bgypfwProducsupplierService")
public class BgypfwProducsupplierServiceImpl implements BgypfwProducsupplierService {
	@Autowired
	private BgypfwProducsupplierDao bgypfwProducsupplierDao;
	
	@Override
	public BgypfwProducsupplierEntity queryObject(Integer id){
		return bgypfwProducsupplierDao.queryObject(id);
	}
	
	@Override
	public List<BgypfwProducsupplierEntity> queryList(Map<String, Object> map){
		return bgypfwProducsupplierDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwProducsupplierDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwProducsupplierEntity bgypfwProducsupplier){
		bgypfwProducsupplierDao.save(bgypfwProducsupplier);
	}
	
	@Override
	public void update(BgypfwProducsupplierEntity bgypfwProducsupplier){
		bgypfwProducsupplierDao.update(bgypfwProducsupplier);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwProducsupplierDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwProducsupplierDao.deleteBatch(ids);
	}
	
}
