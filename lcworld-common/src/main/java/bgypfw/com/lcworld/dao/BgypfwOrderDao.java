package com.lcworld.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.BgypfwOrderDTO;
import com.lcworld.dto.HysfwOrderDTO;
import com.lcworld.entity.BgypfwOrderEntity;
import com.lcworld.utils.Query;

/**
 * 办公用品服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public interface BgypfwOrderDao extends BaseDao<BgypfwOrderEntity> {

	int invalidOrderBatch(HashMap<String, Object> params);

	List<BgypfwOrderEntity> OrderList(Map<String, Object> map);

	List<BgypfwOrderEntity> queryOrderlist(Query q);

	BgypfwOrderDTO queryOrderDetail(JSONObject params);

	void saveOrderCancel(Map<String, Object> param);

	void instantUpdateOrder(Map<String, Object> param);

	void distributionOrder(JSONObject in);

	void deleteDeliveryOrder(Map<String, Object> param);

	void finishDeliveryOrders(JSONObject p );

	void deleteCancelOrder(JSONObject in);

	void deleteOverOrder(JSONObject in);

	java.lang.Integer selectCountByTypeAndOrderId(Map<String, Object> param);

	void insertDeliveryOrder(Map<String, Object> param);

	void insertRefuseOrder(JSONObject p);
	
}
