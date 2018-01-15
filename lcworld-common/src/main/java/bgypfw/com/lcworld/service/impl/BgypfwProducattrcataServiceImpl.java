package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BgypfwProducattrcataDao;
import com.lcworld.entity.BgypfwProducattrcataEntity;
import com.lcworld.service.BgypfwProducattrcataService;



@Service("bgypfwProducattrcataService")
public class BgypfwProducattrcataServiceImpl implements BgypfwProducattrcataService {
	@Autowired
	private BgypfwProducattrcataDao bgypfwProducattrcataDao;
	
	@Override
	public BgypfwProducattrcataEntity queryObject(Integer id){
		return bgypfwProducattrcataDao.queryObject(id);
	}
	
	@Override
	public List<BgypfwProducattrcataEntity> queryList(Map<String, Object> map){
		return bgypfwProducattrcataDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwProducattrcataDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwProducattrcataEntity bgypfwProducattrcata){
		bgypfwProducattrcataDao.save(bgypfwProducattrcata);
	}
	
	@Override
	public void update(BgypfwProducattrcataEntity bgypfwProducattrcata){
		bgypfwProducattrcataDao.update(bgypfwProducattrcata);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwProducattrcataDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwProducattrcataDao.deleteBatch(ids);
	}

	
}
