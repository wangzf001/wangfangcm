package com.lcworld.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.DsfwOrderDTO;
import com.lcworld.entity.DsfwOrderEntity;
import com.lcworld.entity.DsfwSendtimeEntity;
import com.lcworld.utils.Query;

/**
 * 订水服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:20
 */
public interface DsfwOrderDao extends BaseDao<DsfwOrderEntity> {

	int invalidOrderBatch(HashMap<String, Object> params);

	DsfwSendtimeEntity getSendtime(Integer sendtimeid);

	List<DsfwOrderEntity> queryOrderlist(Query q);

	DsfwOrderDTO queryOrderDetail(JSONObject params);

	void saveOrderCancel(Map<String, Object> param);

	void instantUpdateOrder(Map<String, Object> param);

	void deleteDeliveryOrder(Map<String, Object> param);

	void distributionOrder(JSONObject in);

	void finishDeliveryOrders(JSONObject p);

	void deleteCancelOrder(JSONObject in);

	void deleteOverOrder(JSONObject in);

	java.lang.Integer selectCountByOrderIdAndOrderType(Map<String, Object> param);

	void insertDeliveryOrder(Map<String, Object> param);

	void insertRefuseOrder(JSONObject p);
	
}
