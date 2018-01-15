package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BgypfwCartDao;
import com.lcworld.entity.BgypfwCartEntity;
import com.lcworld.service.BgypfwCartService;



@Service("bgypfwCartService")
public class BgypfwCartServiceImpl implements BgypfwCartService {
	@Autowired
	private BgypfwCartDao bgypfwCartDao;
	
	@Override
	public BgypfwCartEntity queryObject(Integer id){
		return bgypfwCartDao.queryObject(id);
	}
	
	@Override
	public List<BgypfwCartEntity> queryList(Map<String, Object> map){
		return bgypfwCartDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwCartDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwCartEntity bgypfwCart){
		bgypfwCartDao.save(bgypfwCart);
	}
	
	@Override
	public void update(BgypfwCartEntity bgypfwCart){
		bgypfwCartDao.update(bgypfwCart);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwCartDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwCartDao.deleteBatch(ids);
	}
	
}
