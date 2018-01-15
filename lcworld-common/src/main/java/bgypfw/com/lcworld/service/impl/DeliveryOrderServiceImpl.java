package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lcworld.consts.APPConstant;
import com.lcworld.dao.DeliveryOrderDao;
import com.lcworld.entity.DeliveryOrderEntity;
import com.lcworld.factory.OrderServiceFactory;
import com.lcworld.service.DeliveryOrderService;
import com.lcworld.service.IOrderService;



@Service("deliveryOrderService")
public class DeliveryOrderServiceImpl implements DeliveryOrderService {
	@Autowired
	private DeliveryOrderDao deliveryOrderDao;
	@Autowired
	private OrderServiceFactory orderServiceFactory;
	@Override
	public DeliveryOrderEntity queryObject(Integer id){
		return deliveryOrderDao.queryObject(id);
	}
	
	@Override
	public List<DeliveryOrderEntity> queryList(Map<String, Object> map){
		return deliveryOrderDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return deliveryOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(DeliveryOrderEntity deliveryOrder){
		IOrderService service = orderServiceFactory.getService(deliveryOrder.getOrdertype());
		deliveryOrder.setDeliverytime(service.getDeliverytime(deliveryOrder.getOrderid()));
		deliveryOrderDao.save(deliveryOrder);
		service.updateOrderStatus(deliveryOrder.getOrderid(), APPConstant.TYPE_ORDER_STATUS_ORDERED, APPConstant.TYPE_ORDER_STATUS_SERVING);
	}
	
	@Override
	public void update(DeliveryOrderEntity deliveryOrder){
		deliveryOrderDao.update(deliveryOrder);
	}
	
	@Override
	public void delete(Integer id){
		deliveryOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		deliveryOrderDao.deleteBatch(ids);
	}
	
}
