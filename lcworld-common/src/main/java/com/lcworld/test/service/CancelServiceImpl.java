package com.lcworld.test.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lcworld.dao.BgypfwOrderDao;
import com.lcworld.test.queue.vo.CancelOrderVo;

@Service
public class CancelServiceImpl {
	@Resource
	private BgypfwOrderDao bgypfwOrderDao;
	public void cancelOrder(CancelOrderVo cancelOrderVo){
		
	}
}
