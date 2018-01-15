package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.ServiceCancelReasonDao;
import com.lcworld.entity.ServiceCancelReasonEntity;
import com.lcworld.entity.ServiceEntity;
import com.lcworld.service.ServiceCancelReasonService;



@Service("serviceCancelReasonService")
public class ServiceCancelReasonServiceImpl implements ServiceCancelReasonService {
	@Autowired
	private ServiceCancelReasonDao serviceCancelReasonDao;
	
	@Override
	public ServiceCancelReasonEntity queryObject(Integer id){
		return serviceCancelReasonDao.queryObject(id);
	}
	
	@Override
	public List<ServiceCancelReasonEntity> queryList(Map<String, Object> map){
		return serviceCancelReasonDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return serviceCancelReasonDao.queryTotal(map);
	}
	
	@Override
	public void save(ServiceCancelReasonEntity serviceCancelReason){
		serviceCancelReasonDao.save(serviceCancelReason);
	}
	
	@Override
	public void update(ServiceCancelReasonEntity serviceCancelReason){
		serviceCancelReasonDao.update(serviceCancelReason);
	}
	
	@Override
	public void delete(Integer id){
		serviceCancelReasonDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		serviceCancelReasonDao.deleteBatch(ids);
	}

    @Override
    public List<ServiceCancelReasonEntity> queryServiceCancelReason(Integer typeJkzx) {
       JSONObject params = new JSONObject();
       params.put("type", typeJkzx);
       return serviceCancelReasonDao.queryList(params);
    }

    
	
}
