package com.lcworld.service;

import com.lcworld.entity.ServiceCancelReasonEntity;
import com.lcworld.entity.ServiceEntity;

import java.util.List;
import java.util.Map;

/**
 * 取消原因表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-18 14:19:15
 */
public interface ServiceCancelReasonService {
	
	ServiceCancelReasonEntity queryObject(Integer id);
	
	List<ServiceCancelReasonEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ServiceCancelReasonEntity serviceCancelReason);
	
	void update(ServiceCancelReasonEntity serviceCancelReason);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	List<ServiceCancelReasonEntity> queryServiceCancelReason(Integer typeJkzx);
}
