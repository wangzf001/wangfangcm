package com.lcworld.test.queue.interf;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.test.queue.vo.CancelOrderVo;
import com.lcworld.utils.R;

public interface CancelOrderService {
	CancelOrderVo changeToCancelOrderVo(Object obj);
	List<CancelOrderVo> queryOrderVoList(HashMap<String, Object> params);
	R orderCancel(JSONObject params);
}
