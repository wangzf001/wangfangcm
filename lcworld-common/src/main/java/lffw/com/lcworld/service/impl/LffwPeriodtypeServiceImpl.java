package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.LffwPeriodtypeDao;
import com.lcworld.dto.LffwscheduleDTO;
import com.lcworld.entity.LffwPeriodtypeEntity;
import com.lcworld.service.LffwPeriodtypeService;



@Service("lffwPeriodtypeService")
public class LffwPeriodtypeServiceImpl implements LffwPeriodtypeService {
	@Autowired
	private LffwPeriodtypeDao lffwPeriodtypeDao;
	
	@Override
	public LffwPeriodtypeEntity queryObject(Integer id){
		return lffwPeriodtypeDao.queryObject(id);
	}
	
	@Override
	public List<LffwPeriodtypeEntity> queryList(Map<String, Object> map){
		return lffwPeriodtypeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwPeriodtypeDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwPeriodtypeEntity lffwPeriodtype){
		lffwPeriodtypeDao.save(lffwPeriodtype);
	}
	
	@Override
	public void update(LffwPeriodtypeEntity lffwPeriodtype){
		lffwPeriodtypeDao.update(lffwPeriodtype);
	}
	
	@Override
	public void delete(Integer id){
		lffwPeriodtypeDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwPeriodtypeDao.deleteBatch(ids);
	}

    @Override
    public List<LffwscheduleDTO> querytimelist(JSONObject params) {
       return lffwPeriodtypeDao.querytimelist(params);
    }
	
}
