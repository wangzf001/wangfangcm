package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.dao.HealthyinfoDao;
import com.lcworld.dto.HealthyinfoDTO;
import com.lcworld.entity.HealthyinfoEntity;
import com.lcworld.service.HealthyinfoService;
import com.lcworld.utils.Query;



@Service("healthyinfoService")
public class HealthyinfoServiceImpl implements HealthyinfoService {
	@Autowired
	private HealthyinfoDao healthyinfoDao;
	
	@Override
	public HealthyinfoEntity queryObject(Integer id){
		return healthyinfoDao.queryObject(id);
	}
	
	@Override
	public List<HealthyinfoEntity> queryList(Map<String, Object> map){
		return healthyinfoDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return healthyinfoDao.queryTotal(map);
	}
	
	@Override
	public void save(HealthyinfoEntity healthyinfo){
		healthyinfoDao.save(healthyinfo);
	}
	
	@Override
	public void update(HealthyinfoEntity healthyinfo){
		healthyinfoDao.update(healthyinfo);
	}
	
	@Override
	public void delete(Integer id){
		healthyinfoDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		healthyinfoDao.deleteBatch(ids);
	}

    @Override
    public List<HealthyinfoDTO> queryInfoList(Query q) {
       return healthyinfoDao.queryInfoList(q);
    }
	
}
