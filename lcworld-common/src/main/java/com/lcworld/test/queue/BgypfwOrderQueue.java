package com.lcworld.test.queue;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BgypfwOrderQueue extends ConcurrentLinkedQueue<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Object searchElement(Integer id){
		if (id==null||id<=0) {
			return null;
		}
		Iterator<Object> iterator = this.iterator();
		while (iterator.hasNext()) {
			Object order = iterator.next();
			if (id.equals(1)) {
				return order;
			}
		}
		return null;
	}
}
