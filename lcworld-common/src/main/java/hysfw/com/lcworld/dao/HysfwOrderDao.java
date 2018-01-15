package com.lcworld.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.HysfwOrderDTO;
import com.lcworld.entity.HysfwOrderEntity;
import com.lcworld.utils.Query;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
public interface HysfwOrderDao extends BaseDao<HysfwOrderEntity> {

	int invalidOrderBatch(HashMap<String, Object> params);

	List<HysfwOrderDTO> queryOrderlist(Query q);

	HysfwOrderDTO queryOrderDetail(JSONObject params);

	void saveOrderCancel(Map<String, Object> param);

	void refuseOrder(JSONObject p);

	void deleteDeliveryOrder(Map<String, Object> param);

	void finishDeliveryOrders(JSONObject p);

	void deleteCancelOrder(JSONObject in);

	void deleteOverOrder(JSONObject in);
	
}
