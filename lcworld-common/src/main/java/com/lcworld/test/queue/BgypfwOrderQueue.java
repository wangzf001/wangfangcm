package com.lcworld.test.queue;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.lcworld.entity.BgypfwOrderEntity;
import com.lcworld.entity.TDcfwOrderEntity;

public class BgypfwOrderQueue extends ConcurrentLinkedQueue<BgypfwOrderEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Object searchElement(Integer id){
		if (id==null||id<=0) {
			return null;
		}
		Iterator<BgypfwOrderEntity> iterator = this.iterator();
		while (iterator.hasNext()) {
			BgypfwOrderEntity order = iterator.next();
			if (id.equals(order.getId())) {
				return order;
			}
		}
		return null;
	}
}
