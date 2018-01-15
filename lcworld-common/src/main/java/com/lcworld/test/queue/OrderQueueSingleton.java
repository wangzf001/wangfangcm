package com.lcworld.test.queue;

public class OrderQueueSingleton{
	private static CancelQueue cancelQueue = new CancelQueue();
	private static RemindQueue remindQueue = new RemindQueue();
	private OrderQueueSingleton(){
		
	}
	public static CancelQueue getCancelQueue(){
		return cancelQueue;
	}
	public static RemindQueue getRemindQueue(){
		return remindQueue;
	}
}
