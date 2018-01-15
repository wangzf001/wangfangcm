package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.BxwxOrderDTO;
import com.lcworld.entity.TBxwxOrderEntity;
import com.lcworld.utils.Query;

/**
 * 报修维修订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 11:45:49
 */
public interface TBxwxOrderDao extends BaseDao<TBxwxOrderEntity> {

    List<BxwxOrderDTO> queryOrderlist(Query q);

    BxwxOrderDTO queryOrderDetail(JSONObject params);

    List<Map<String, Object>> queryordermapList(Query query);

    int queryordermapTotal(Query query);

	List<TBxwxOrderEntity> OrderList(Map<String, Object> map);

	BxwxOrderDTO orderDetail(String ordercode);

	void deleteOrder(String ordercode);

	void completeOrder(String biz);

	void saveOrderCancel(Map<String, Object> param);

	void updateOrderMenderIdNull(JSONObject p);

	void cancelOrder(JSONObject in);

	void deleteProcesses(JSONObject p);

	void deleteCancelOrder(JSONObject in);

	void deleteOverOrder(JSONObject in);

	void refuseOrder(JSONObject p);
	
}
