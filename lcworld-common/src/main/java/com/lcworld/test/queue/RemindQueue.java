package com.lcworld.test.queue;

import java.util.Iterator;
import java.util.PriorityQueue;

import com.lcworld.test.queue.vo.RemindOrderVo;

public class RemindQueue extends PriorityQueue<RemindOrderVo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	public Object searchElement(Integer oid){
//		if (oid==null||oid<=0) {
//			return null;
//		}
//		Iterator<RemindOrderVo> iterator = this.iterator();
//		while (iterator.hasNext()) {
//			RemindOrderVo order = iterator.next();
//			if (oid.equals(order.getOrderid())) {
//				return order;
//			}
//		}
//		return null;
//	}
}
