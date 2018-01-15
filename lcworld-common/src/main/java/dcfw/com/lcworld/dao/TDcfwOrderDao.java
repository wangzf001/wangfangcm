package com.lcworld.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TDcfwOrderEntity;
import com.lcworld.utils.Query;

/**
 * 订餐服务订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
public interface TDcfwOrderDao extends BaseDao<TDcfwOrderEntity> {

	void cancelOrder(Integer[] cancelOrder);

	int updateOrderBatch(HashMap<String, Object> params);

	void updateOrdersFinish(Map<String, Object> params);
	
}
