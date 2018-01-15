package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BgypfwProductimgDao;
import com.lcworld.entity.BgypfwProductimgEntity;
import com.lcworld.service.BgypfwProductimgService;



@Service("bgypfwProductimgService")
public class BgypfwProductimgServiceImpl implements BgypfwProductimgService {
	@Autowired
	private BgypfwProductimgDao bgypfwProductimgDao;
	
	@Override
	public BgypfwProductimgEntity queryObject(Integer id){
		return bgypfwProductimgDao.queryObject(id);
	}
	
	@Override
	public List<BgypfwProductimgEntity> queryList(Map<String, Object> map){
		return bgypfwProductimgDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwProductimgDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwProductimgEntity bgypfwProductimg){
		bgypfwProductimgDao.save(bgypfwProductimg);
	}
	
	@Override
	public void update(BgypfwProductimgEntity bgypfwProductimg){
		bgypfwProductimgDao.update(bgypfwProductimg);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwProductimgDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwProductimgDao.deleteBatch(ids);
	}

	@Override
	public void deleteByPids(Integer[] ids) {
		bgypfwProductimgDao.deleteByPids(ids);
	}

}
