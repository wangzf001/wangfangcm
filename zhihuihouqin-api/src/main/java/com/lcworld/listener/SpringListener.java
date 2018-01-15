package com.lcworld.listener;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.lcworld.service.TDcfwOrderService;
import com.lcworld.service.YlfwZjzzOrderService;
import com.lcworld.test.queue.CancelQueue;
import com.lcworld.test.queue.OrderQueueSingleton;
import com.lcworld.test.queue.RemindQueue;
import com.lcworld.test.queue.interf.CancelOrderService;
import com.lcworld.test.queue.interf.RemindOrderService;
import com.lcworld.test.queue.vo.CancelOrderVo;
import com.lcworld.test.queue.vo.RemindOrderVo;
import com.lcworld.utils.DateUtil;
@Service
public class SpringListener implements ApplicationListener<ContextRefreshedEvent > {
	private Logger log = LoggerFactory.getLogger(SpringListener.class);
	@Resource
	private YlfwZjzzOrderService ylfwZjzzOrderService;
	@Resource
	private TDcfwOrderService tDcfwOrderService;
	@Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
		//spring会加载两次创建两个容器root application context 没有parent，他就是老大.
		if(event.getApplicationContext().getParent() == null){
			ApplicationContext apcontext = event.getApplicationContext();
			//加载需要提醒的订单
			HashMap<String,Object> params = new HashMap<>();
			params.put("excuteTimeAddHalf", DateUtil.add_minute(30));
			Map<String, RemindOrderService> remindMap = apcontext.getBeansOfType(RemindOrderService.class);
			log.debug("--------ADD---RemindServiceList:"+remindMap);
			if (remindMap!=null) {
				Set<Entry<String,RemindOrderService>> entrySet = remindMap.entrySet();
				for (Entry<String, RemindOrderService> entry : entrySet) {
					try {
						RemindOrderService service = entry.getValue();
						List<RemindOrderVo> remindlist = service.queryRemindOrderVoList(params);
						RemindQueue remindQueue = OrderQueueSingleton.getRemindQueue();
						remindQueue.addAll(remindlist);
					} catch (Exception e) {
						log.error("RemindQueueAddErr:"+e);
					}
				}
			}
			//加载需要设置失效的订单
			HashMap<String,Object> params1 = new HashMap<>();
			params1.put("timeAlarm", new Date());
			Map<String, CancelOrderService> cancelMap = apcontext.getBeansOfType(CancelOrderService.class);
			log.debug("--------ADD---CancelOrderServiceList:"+cancelMap);
			if (cancelMap!=null) {
				Set<Entry<String,CancelOrderService>> entrySet = cancelMap.entrySet();
				for (Entry<String, CancelOrderService> entry : entrySet) {
					try {
						CancelOrderService service = entry.getValue();
						List<CancelOrderVo> cancelList = service.queryOrderVoList(params1);
						CancelQueue cancelQueue = OrderQueueSingleton.getCancelQueue();
						cancelQueue.addAll(cancelList);
					} catch (Exception e) {
						log.error("CancelOrderQueueAddErr:"+e);
					}
				}
			}
		}
//		List<RemindOrderVo> remindlist = ylfwZjzzOrderService.queryOrderVoList(params);
//		RemindQueue remindQueue = OrderQueueSingleton.getRemindQueue();
//		remindQueue.addAll(remindlist);
//		//加载需要设置失效的订单
//		HashMap<String,Object> params1 = new HashMap<>();
//		params1.put("timeAlarm", new Date());
//		List<CancelOrderVo> cancelList = tDcfwOrderService.queryOrderVoList(params1);
//		CancelQueue cancelQueue = OrderQueueSingleton.getCancelQueue();
//		cancelQueue.addAll(cancelList);
	}
}
