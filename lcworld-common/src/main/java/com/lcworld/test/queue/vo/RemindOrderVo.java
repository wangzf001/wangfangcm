package com.lcworld.test.queue.vo;

import java.util.Date;
import java.util.PriorityQueue;

import com.lcworld.utils.DateUtil;

public class RemindOrderVo  extends BaseOrderVo{
	private String mobile;
	private Integer email;
	private String remindmsg;
	
	
	public String getRemindmsg() {
        return remindmsg;
    }

    public void setRemindmsg(String remindmsg) {
        this.remindmsg = remindmsg;
    }

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getEmail() {
		return email;
	}

	public void setEmail(Integer email) {
		this.email = email;
	}
	public static void main(String[] args) {
//		RemindOrderVo orderVo = new RemindOrderVo();
//		RemindOrderVo orderVo1 = new RemindOrderVo();
//		orderVo.setTimeAlarm(DateUtil.add_minute(1));
//		orderVo.setOrderid(2);
//		orderVo1.setTimeAlarm(new Date());
//		orderVo1.setOrderid(1);
//		int compareTo = orderVo.compareTo(orderVo1);
//		PriorityQueue<RemindOrderVo> queue = new PriorityQueue<>();
//		queue.offer(orderVo1);
//		queue.offer(orderVo);
//		System.out.println(queue.poll().getOrderid());
//		System.out.println(queue.poll().getOrderid());
	}
	
}
