package com.lcworld.task;

import org.springframework.stereotype.Component;

@Component
public class OrderCancelTask {
	
	public void orderCancel(){
		System.out.println("执行了WEB中的OrderCancel方法");
	}
}
