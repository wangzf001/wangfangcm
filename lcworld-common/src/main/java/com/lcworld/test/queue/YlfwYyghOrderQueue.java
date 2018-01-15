package com.lcworld.test.queue;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.lcworld.entity.BgypfwOrderEntity;
import com.lcworld.entity.TDcfwOrderEntity;
import com.lcworld.entity.YlfwYyghOrderEntity;

public class YlfwYyghOrderQueue extends ConcurrentLinkedQueue<YlfwYyghOrderEntity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Object searchElement(Integer id){
		if (id==null||id<=0) {
			return null;
		}
		Iterator<YlfwYyghOrderEntity> iterator = this.iterator();
		while (iterator.hasNext()) {
			YlfwYyghOrderEntity order = iterator.next();
			if (id.equals(order.getId())) {
				return order;
			}
		}
		return null;
	}
}
