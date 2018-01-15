package com.lcworld.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.FwOrderCancelReasonDao;
import com.lcworld.service.CancelOrderReasonService;

@Service("tFwOrderCancelReason")
public class CancelOrderReasonServiceImpl implements CancelOrderReasonService {
	
	@Autowired
	FwOrderCancelReasonDao fwOrderCancelReasonDao;

	@Override
	public List<Map<String, Object>> getCancelOrderReason(JSONObject p) {
		return fwOrderCancelReasonDao.getCancelOrderReason(p);
	}

}
