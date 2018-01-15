package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BgypfwSkufirstcataDao;
import com.lcworld.entity.BgypfwSkufirstcataEntity;
import com.lcworld.service.BgypfwSkufirstcataService;



@Service("bgypfwSkufirstcataService")
public class BgypfwSkufirstcataServiceImpl implements BgypfwSkufirstcataService {
	@Autowired
	private BgypfwSkufirstcataDao bgypfwSkufirstcataDao;
	
	@Override
	public BgypfwSkufirstcataEntity queryObject(Integer id){
		return bgypfwSkufirstcataDao.queryObject(id);
	}
	
	@Override
	public List<BgypfwSkufirstcataEntity> queryList(Map<String, Object> map){
		return bgypfwSkufirstcataDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwSkufirstcataDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwSkufirstcataEntity bgypfwSkufirstcata){
		bgypfwSkufirstcataDao.save(bgypfwSkufirstcata);
	}
	
	@Override
	public void update(BgypfwSkufirstcataEntity bgypfwSkufirstcata){
		bgypfwSkufirstcataDao.update(bgypfwSkufirstcata);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwSkufirstcataDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwSkufirstcataDao.deleteBatch(ids);
	}
	
}
