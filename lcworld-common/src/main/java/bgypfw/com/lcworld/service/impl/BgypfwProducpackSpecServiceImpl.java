package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BgypfwProducpackSpecDao;
import com.lcworld.entity.BgypfwProducpackSpecEntity;
import com.lcworld.service.BgypfwProducpackSpecService;



@Service("bgypfwProducpackSpecService")
public class BgypfwProducpackSpecServiceImpl implements BgypfwProducpackSpecService {
	@Autowired
	private BgypfwProducpackSpecDao bgypfwProducpackSpecDao;
	
	@Override
	public BgypfwProducpackSpecEntity queryObject(Integer id){
		return bgypfwProducpackSpecDao.queryObject(id);
	}
	
	@Override
	public List<BgypfwProducpackSpecEntity> queryList(Map<String, Object> map){
		return bgypfwProducpackSpecDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwProducpackSpecDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwProducpackSpecEntity bgypfwProducpackSpec){
		bgypfwProducpackSpecDao.save(bgypfwProducpackSpec);
	}
	
	@Override
	public void update(BgypfwProducpackSpecEntity bgypfwProducpackSpec){
		bgypfwProducpackSpecDao.update(bgypfwProducpackSpec);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwProducpackSpecDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwProducpackSpecDao.deleteBatch(ids);
	}
	
}
