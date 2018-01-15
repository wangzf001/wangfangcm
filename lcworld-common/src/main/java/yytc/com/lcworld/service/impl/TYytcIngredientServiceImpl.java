package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TYytcIngredientDao;
import com.lcworld.entity.TYytcIngredientEntity;
import com.lcworld.service.TYytcIngredientService;



@Service("tYytcIngredientService")
public class TYytcIngredientServiceImpl implements TYytcIngredientService {
	@Autowired
	private TYytcIngredientDao tYytcIngredientDao;
	
	@Override
	public TYytcIngredientEntity queryObject(Integer iId){
		return tYytcIngredientDao.queryObject(iId);
	}
	
	@Override
	public List<TYytcIngredientEntity> queryList(Map<String, Object> map){
		return tYytcIngredientDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tYytcIngredientDao.queryTotal(map);
	}
	
	@Override
	public void save(TYytcIngredientEntity tYytcIngredient){
		tYytcIngredientDao.save(tYytcIngredient);
	}
	
	@Override
	public void update(TYytcIngredientEntity tYytcIngredient){
		tYytcIngredientDao.update(tYytcIngredient);
	}
	
	@Override
	public void delete(Integer iId){
		tYytcIngredientDao.delete(iId);
	}
	
	@Override
	public void deleteBatch(Integer[] iIds){
		tYytcIngredientDao.deleteBatch(iIds);
	}
	
}
