package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.JyfwComplaintagDao;
import com.lcworld.entity.JyfwComplaintagEntity;
import com.lcworld.service.JyfwComplaintagService;



@Service("jyfwComplaintagService")
public class JyfwComplaintagServiceImpl implements JyfwComplaintagService {
	@Autowired
	private JyfwComplaintagDao jyfwComplaintagDao;
	
	@Override
	public JyfwComplaintagEntity queryObject(Integer id){
		return jyfwComplaintagDao.queryObject(id);
	}
	
	@Override
	public List<JyfwComplaintagEntity> queryList(Map<String, Object> map){
		return jyfwComplaintagDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return jyfwComplaintagDao.queryTotal(map);
	}
	
	@Override
	public void save(JyfwComplaintagEntity jyfwComplaintag){
		jyfwComplaintagDao.save(jyfwComplaintag);
	}
	
	@Override
	public void update(JyfwComplaintagEntity jyfwComplaintag){
		jyfwComplaintagDao.update(jyfwComplaintag);
	}
	
	@Override
	public void delete(Integer id){
		jyfwComplaintagDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		jyfwComplaintagDao.deleteBatch(ids);
	}
	
}
