package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.dao.LffwBarberDao;
import com.lcworld.dto.LffwBarberDTO;
import com.lcworld.entity.LffwBarberEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.service.LffwBarberService;
import com.lcworld.service.ServiceService;
import com.lcworld.utils.Query;



@Service("lffwBarberService")
public class LffwBarberServiceImpl implements LffwBarberService {
	@Autowired
	private LffwBarberDao lffwBarberDao;
	@Autowired
	private ServiceService serviceService;
	
	@Override
	public LffwBarberEntity queryObject(Integer id){
		return lffwBarberDao.queryObject(id);
	}
	
	@Override
	public List<LffwBarberEntity> queryList(Map<String, Object> map){
		ServiceEntity service = serviceService.queryService(APPConstant.TYPE_LFFW_YYLF);
		List<LffwBarberEntity> list = lffwBarberDao.queryList(map);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setAddress(service.getPlace());
		}
		return list;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return lffwBarberDao.queryTotal(map);
	}
	
	@Override
	public void save(LffwBarberEntity lffwBarber){
		lffwBarberDao.save(lffwBarber);
	}
	
	@Override
	public void update(LffwBarberEntity lffwBarber){
		lffwBarberDao.update(lffwBarber);
	}
	
	@Override
	public void delete(Integer id){
		lffwBarberDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		lffwBarberDao.deleteBatch(ids);
	}

    @Override
    public List<LffwBarberDTO> querybarberList(JSONObject parmas) {
    	ServiceEntity service = serviceService.queryService(APPConstant.TYPE_LFFW_YYLF);
		List<LffwBarberDTO> list = lffwBarberDao.querybarberList(parmas);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setAddress(service.getPlace());
		}
        return list;
    }

	@Override
	public List<LffwBarberEntity> queryFavorList(Query params) {
		return queryList(params);
	}
	
}
