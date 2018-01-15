package com.lcworld.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TDcfwOrderEntity;
import com.lcworld.test.queue.interf.CancelOrderService;
import com.lcworld.test.queue.interf.RemindOrderService;
import com.lcworld.test.queue.vo.CancelOrderVo;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;

/**
 * 订餐服务订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
public interface TDcfwOrderService extends IOrderService,CancelOrderService,RemindOrderService<TDcfwOrderEntity>{
	
	TDcfwOrderEntity queryObject(Integer id);
	
	List<TDcfwOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TDcfwOrderEntity tDcfwOrder);
	
	void update(TDcfwOrderEntity tDcfwOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 生成订单
	 * @param params
	 */
	R generateOrder(JSONObject params);
//	/**
//	 * 取消订单
//	 * @param params
//	 * @return
//	 */
//	R orderCancel(JSONObject params);
	/**
	 * 查询所有
	 * @param params1
	 * @return
	 */
	List<CancelOrderVo> queryOrderVoList(HashMap<String, Object> params1);

	int invalidOrderBatch(Integer[] array);

	void updateBatch(Integer[] ids, int typeOrderStatusFinished);

	void finishOrders(JSONObject p);
}
