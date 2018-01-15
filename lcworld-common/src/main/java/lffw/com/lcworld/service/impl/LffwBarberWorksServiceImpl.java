package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.LffwBarberWorksDao;
import com.lcworld.entity.LffwBarberWorksEntity;
import com.lcworld.service.LffwBarberWorksService;
import com.lcworld.utils.Query;



@Service("lffwBarberWorksService")
public class LffwBarberWorksServiceImpl implements LffwBarberWorksService {
	@Autowired
	private LffwBarberWorksDao lffwBarberWorksDao;
	
	@Override
	public LffwBarberWorksEntity queryObject(Integer id){
		return lffwBarberWorksDao.queryObject(id);
	}
	
	@Override
	public List<LffwBarberWorksEntity> queryList(Map<String, Object> map){
		return lffwBarberWorksDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwBarberWorksDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwBarberWorksEntity lffwBarberWorks){
		lffwBarberWorksDao.save(lffwBarberWorks);
	}
	
	@Override
	public void update(LffwBarberWorksEntity lffwBarberWorks){
		lffwBarberWorksDao.update(lffwBarberWorks);
	}
	
	@Override
	public void delete(Integer id){
		lffwBarberWorksDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwBarberWorksDao.deleteBatch(ids);
	}

    @Override
    public List<LffwBarberWorksEntity> queryindexworkList(JSONObject params) {
       return lffwBarberWorksDao.queryindexworkList(params);
    }

	@Override
	public List<LffwBarberWorksEntity> queryFavorList(Query params) {
		return queryList(params);
	}
	
}
