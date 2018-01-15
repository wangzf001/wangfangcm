package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.BgypfwCategoryDao;
import com.lcworld.entity.BgypfwCategoryEntity;
import com.lcworld.service.BgypfwCategoryService;



@Service("bgypfwCategoryService")
public class BgypfwCategoryServiceImpl implements BgypfwCategoryService {
	@Autowired
	private BgypfwCategoryDao bgypfwCategoryDao;
	
	@Override
	public BgypfwCategoryEntity queryObject(Integer id){
		return bgypfwCategoryDao.queryObject(id);
	}

	@Override
	public BgypfwCategoryEntity queryCategoryByName(Map<String,Object> params){
		return bgypfwCategoryDao.queryCategoryByName(params);
	}

	@Override
	public List<BgypfwCategoryEntity> queryList(Map<String, Object> map){
		return bgypfwCategoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bgypfwCategoryDao.queryTotal(map);
	}
	
	@Override
	public void save(BgypfwCategoryEntity bgypfwCategory){
		bgypfwCategoryDao.save(bgypfwCategory);
	}
	
	@Override
	public void update(BgypfwCategoryEntity bgypfwCategory){
		bgypfwCategoryDao.update(bgypfwCategory);
	}
	
	@Override
	public void delete(Integer id){
		bgypfwCategoryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bgypfwCategoryDao.deleteBatch(ids);
	}
	
}
