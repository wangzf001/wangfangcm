package com.lcworld.test.queue.interf;

import java.util.HashMap;
import java.util.List;

import com.lcworld.test.queue.vo.RemindOrderVo;

public interface RemindOrderService<T> {
	RemindOrderVo changeToRemindOrderVo(T t);
	//取消提醒
	void remindCancel(T t);
	List<RemindOrderVo> queryRemindOrderVoList(HashMap<String, Object> params);
}
