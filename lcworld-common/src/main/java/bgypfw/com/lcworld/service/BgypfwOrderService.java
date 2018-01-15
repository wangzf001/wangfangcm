package com.lcworld.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.BgypfwOrderEntity;
import com.lcworld.utils.R;

/**
 * 办公用品服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public interface BgypfwOrderService extends IOrderService{
	
	BgypfwOrderEntity queryObject(Integer id);
	
	List<BgypfwOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwOrderEntity bgypfwOrder);
	
	void update(BgypfwOrderEntity bgypfwOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 生成订单
	 * @param params
	 * @return
	 * @throws ParseException
	 */
	R generateOrder(JSONObject params) throws ParseException;
	/**
	 * 取消订单
	 * @param params
	 * @return
	 */
	R orderCancel(JSONObject params);
	/**
	 * 无效化订单（删除订单）
	 * @param array
	 * @return
	 */
	int invalidOrderBatch(Integer[] array);
}
