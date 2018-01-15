package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.DsfwAppointmentEntity;
import com.lcworld.entity.DsfwOrderEntity;
import com.lcworld.entity.HysfwAppointmentEntity;
import com.lcworld.entity.HysfwOrderEntity;
import com.lcworld.utils.R;

import java.util.List;
import java.util.Map;

/**
 * 订水服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:20
 */
public interface DsfwOrderService extends IOrderService {
	
	DsfwOrderEntity queryObject(Integer id);
	
	List<DsfwOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DsfwOrderEntity dsfwOrder);
	
	void update(DsfwOrderEntity dsfwOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 生成订单
	 * @param
	 * @return
	 */
	//R generateOrder(JSONObject params);
	R generateOrder(JSONObject params, List<DsfwAppointmentEntity> appointmentList);
	/**
	 * 取消订单
	 * @param params
	 * @return
	 */
	R orderCancel(JSONObject params);
	/**
	 * 批量删除订单
	 * @param array
	 * @return
	 */
	int invalidOrderBatch(Integer[] array);
}
