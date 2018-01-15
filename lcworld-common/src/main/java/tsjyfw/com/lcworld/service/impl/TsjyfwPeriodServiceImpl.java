package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.TsjyfwPeriodDao;
import com.lcworld.entity.TsjyfwPeriodEntity;
import com.lcworld.service.TsjyfwPeriodService;



@Service("tsjyfwPeriodService")
public class TsjyfwPeriodServiceImpl implements TsjyfwPeriodService {
	@Autowired
	private TsjyfwPeriodDao tsjyfwPeriodDao;
	
	@Override
	public TsjyfwPeriodEntity queryObject(Integer id){
		return tsjyfwPeriodDao.queryObject(id);
	}
	
	@Override
	public List<TsjyfwPeriodEntity> queryList(Map<String, Object> map){
		return tsjyfwPeriodDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tsjyfwPeriodDao.queryTotal(map);
	}
	
	@Override
	public void save(TsjyfwPeriodEntity tsjyfwPeriod){
		tsjyfwPeriodDao.save(tsjyfwPeriod);
	}
	
	@Override
	public void update(TsjyfwPeriodEntity tsjyfwPeriod){
		tsjyfwPeriodDao.update(tsjyfwPeriod);
	}
	
	@Override
	public void delete(Integer id){
		tsjyfwPeriodDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		tsjyfwPeriodDao.deleteBatch(ids);
	}

    @Override
    public List<TsjyfwPeriodEntity> querytimeList(JSONObject map) {
        return tsjyfwPeriodDao.querytimeList(map);
    }
	
}
