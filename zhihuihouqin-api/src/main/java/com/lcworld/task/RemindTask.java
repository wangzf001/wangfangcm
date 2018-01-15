package com.lcworld.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lcworld.test.queue.OrderQueueSingleton;
import com.lcworld.test.queue.RemindQueue;
import com.lcworld.test.queue.vo.RemindOrderVo;
import com.lcworld.utils.SmsUtils;
@Component
public class RemindTask {
	private Logger log = LoggerFactory.getLogger(RemindTask.class);
	public void orderRemid(){
		List<RemindOrderVo> remindOrder = remindOrder(OrderQueueSingleton.getRemindQueue());
		SmsUtils sms = SmsUtils.getInstance();
		String msg = "";
		if (remindOrder.size()!=0) {
			for (RemindOrderVo orderVo : remindOrder) {
				log.debug("单号："+orderVo.getOrdercode()+"电话："+orderVo.getMobile()+"时间"+orderVo.getTimeAlarm()+"定时提醒："+orderVo.getRemindmsg());
				try {
//					sms.send(orderVo.getMobile(), msg);
				} catch (Exception e) {
					log.error("sendSMSFailed:"+e);
				}
			}
		}
	}

	private List<RemindOrderVo> remindOrder(RemindQueue remindQueue) {
		List<RemindOrderVo> list = new ArrayList<RemindOrderVo>();
		RemindOrderVo order = remindQueue.peek();
		if (order==null) {
			return list;
		}
		if (order.getTimeAlarm().getTime()<=new Date().getTime()) {
			RemindOrderVo poll = remindQueue.poll();
			list.add(poll);
			remindOrder(remindQueue);
		}
		return list;
	}
}
