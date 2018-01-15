package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.TGxdfwClothestypeDao;
import com.lcworld.entity.TGxdfwClothestypeEntity;
import com.lcworld.service.TGxdfwClothestypeService;



@Service("tGxdfwClothestypeService")
public class TGxdfwClothestypeServiceImpl implements TGxdfwClothestypeService {
	@Autowired
	private TGxdfwClothestypeDao tGxdfwClothestypeDao;
	
	@Override
	public TGxdfwClothestypeEntity queryObject(Integer id){
		return tGxdfwClothestypeDao.queryObject(id);
	}
	
	@Override
	public List<TGxdfwClothestypeEntity> queryList(Map<String, Object> map){
		return tGxdfwClothestypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tGxdfwClothestypeDao.queryTotal(map);
	}
	
	@Override
	public void save(TGxdfwClothestypeEntity tGxdfwClothestype){
		tGxdfwClothestypeDao.save(tGxdfwClothestype);
	}
	
	@Override
	public void update(TGxdfwClothestypeEntity tGxdfwClothestype){
		tGxdfwClothestypeDao.update(tGxdfwClothestype);
	}
	
	@Override
	public void delete(Integer id){
		tGxdfwClothestypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tGxdfwClothestypeDao.deleteBatch(ids);
	}
	
}
