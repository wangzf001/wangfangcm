package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.OrderViewDao;
import com.lcworld.entity.OrderViewEntity;
import com.lcworld.service.OrderViewService;



@Service("orderViewService")
public class OrderViewServiceImpl implements OrderViewService {
	@Autowired
	private OrderViewDao orderViewDao;
	
	@Override
	public OrderViewEntity queryObject(String id){
		return orderViewDao.queryObject(id);
	}
	
	@Override
	public List<OrderViewEntity> queryList(Map<String, Object> map){
		return orderViewDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return orderViewDao.queryTotal(map);
	}
	
	@Override
	public void save(OrderViewEntity orderView){
		orderViewDao.save(orderView);
	}
	
	@Override
	public void update(OrderViewEntity orderView){
		orderViewDao.update(orderView);
	}
	
	@Override
	public void delete(String id){
		orderViewDao.delete(id);
	}
	
	@Override
	public void deleteBatch(String[] ids){
		orderViewDao.deleteBatch(ids);
	}
	
}
