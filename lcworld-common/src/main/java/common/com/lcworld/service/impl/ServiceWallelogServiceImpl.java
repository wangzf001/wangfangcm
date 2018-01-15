package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.ServiceWallelogDao;
import com.lcworld.entity.ServiceWallelogEntity;
import com.lcworld.service.ServiceWallelogService;
import com.lcworld.vo.service.owner.OwnerWalletHome;
import com.lcworld.vo.service.owner.ServiceOwnerWalletLog;



@Service("serviceWallelogService")
public class ServiceWallelogServiceImpl implements ServiceWallelogService {
	@Autowired
	private ServiceWallelogDao serviceWallelogDao;
	
	@Override
	public ServiceWallelogEntity queryObject(Integer id){
		return serviceWallelogDao.queryObject(id);
	}
	
	@Override
	public List<ServiceWallelogEntity> queryList(Map<String, Object> map){
		return serviceWallelogDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return serviceWallelogDao.queryTotal(map);
	}
	
	@Override
	public void save(ServiceWallelogEntity serviceWallelog){
		serviceWallelogDao.save(serviceWallelog);
	}
	
	@Override
	public void update(ServiceWallelogEntity serviceWallelog){
		serviceWallelogDao.update(serviceWallelog);
	}
	
	@Override
	public void delete(Integer id){
		serviceWallelogDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		serviceWallelogDao.deleteBatch(ids);
	}

	@Override
	public Double walletHome(JSONObject p) {
		p.put("useType", 1);
		Double shouru = serviceWallelogDao.shouru(p);
		
		p.put("useType", 2);
		Double zhichu = serviceWallelogDao.shouru(p);
		
		if(shouru != null && shouru.doubleValue() > 0){
			if(zhichu != null){
				return shouru.doubleValue()-zhichu.doubleValue();
			}else{
				return shouru.doubleValue();
			}
		}
		
		return new Double(0);
	}

	@Override
	public List<ServiceOwnerWalletLog> walletDetail(JSONObject p) {
		return serviceWallelogDao.serviceOwnerWalletLog(p);
	}
	
}
