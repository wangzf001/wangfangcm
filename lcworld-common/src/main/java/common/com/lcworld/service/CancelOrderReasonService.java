package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public interface CancelOrderReasonService {

	List<Map<String, Object>> getCancelOrderReason(JSONObject p);

}
