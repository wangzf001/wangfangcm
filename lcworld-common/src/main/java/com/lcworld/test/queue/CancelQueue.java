package com.lcworld.test.queue;

import java.util.Iterator;
import java.util.PriorityQueue;

import com.lcworld.test.queue.vo.CancelOrderVo;

public class CancelQueue extends PriorityQueue<CancelOrderVo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	public Object searchElement(Integer oid){
//		if (oid==null||oid<=0) {
//			return null;
//		}
//		Iterator<CancelOrderVo> iterator = this.iterator();
//		while (iterator.hasNext()) {
//			CancelOrderVo order = iterator.next();
//			if (oid.equals(order.getOrderid())) {
//				return order;
//			}
//		}
//		return null;
//	}
}
