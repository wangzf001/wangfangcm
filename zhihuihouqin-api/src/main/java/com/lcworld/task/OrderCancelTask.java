package com.lcworld.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.consts.APPConstant;
import com.lcworld.test.queue.CancelQueue;
import com.lcworld.test.queue.OrderQueueSingleton;
import com.lcworld.test.queue.interf.CancelOrderService;
import com.lcworld.test.queue.vo.CancelOrderVo;

@Component
public class OrderCancelTask implements ApplicationContextAware{
	private Logger log = LoggerFactory.getLogger(OrderCancelTask.class);
	private static ApplicationContext applicationContext; // Spring应用上下文环境
	public void orderCancel(){
		List<CancelOrderVo> cancelOrderList = cancelOrder(OrderQueueSingleton.getCancelQueue());
		Map<String, CancelOrderService> cancelServiceMap = applicationContext.getBeansOfType(CancelOrderService.class);
		if (cancelOrderList.size()!=0) {
			for (CancelOrderVo orderVo: cancelOrderList) {
				try {
					CancelOrderService cancelOrderService = cancelServiceMap.get(APPConstant.getOrdertypeServiceMap().get(orderVo.getOrdertype()));
					log.debug("取消订单:"+orderVo.getOrdercode()+"类型:"+orderVo.getOrdertype());
					JSONObject params = new JSONObject();
					params.put("autocancel", 1);
					params.put("ordercode", orderVo.getOrdercode());
					params.put("reasonContent", "超时取消订单");
					cancelOrderService.orderCancel(params);
				} catch (Exception e) {
					log.error("------------------cancelOrderFailed:"+e);
				}
			}
		}
	}

	private List<CancelOrderVo> cancelOrder(CancelQueue cancelQueue) {
		List<CancelOrderVo> list = new ArrayList<CancelOrderVo>();
		CancelOrderVo order = cancelQueue.peek();
		if (order==null) {
			return list;
		}
		if (order.getTimeAlarm().getTime()<=new Date().getTime()) {
			CancelOrderVo poll = cancelQueue.poll();
			list.add(poll);
			cancelOrder(cancelQueue);
		}
		return list;
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		OrderCancelTask.applicationContext = arg0;
	}
}
