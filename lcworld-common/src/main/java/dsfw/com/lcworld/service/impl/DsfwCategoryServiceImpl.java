package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.DsfwCategoryDao;
import com.lcworld.entity.DsfwCategoryEntity;
import com.lcworld.service.DsfwCategoryService;



@Service("dsfwCategoryService")
public class DsfwCategoryServiceImpl implements DsfwCategoryService {
	@Autowired
	private DsfwCategoryDao dsfwCategoryDao;
	
	@Override
	public DsfwCategoryEntity queryObject(Integer id){
		return dsfwCategoryDao.queryObject(id);
	}
	
	@Override
	public List<DsfwCategoryEntity> queryList(Map<String, Object> map){
		return dsfwCategoryDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return dsfwCategoryDao.queryTotal(map);
	}
	
	@Override
	public void save(DsfwCategoryEntity dsfwCategory){
		dsfwCategoryDao.save(dsfwCategory);
	}
	
	@Override
	public void update(DsfwCategoryEntity dsfwCategory){
		dsfwCategoryDao.update(dsfwCategory);
	}
	
	@Override
	public void delete(Integer id){
		dsfwCategoryDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		dsfwCategoryDao.deleteBatch(ids);
	}

	@Override
	public DsfwCategoryEntity queryBygid(JSONObject params) {
		return dsfwCategoryDao.queryBygid(params);
	}
	
}
