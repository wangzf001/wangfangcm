package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TGxdfwCommentEntity;
import com.lcworld.entity.TGxdfwOrderEntity;
import com.lcworld.utils.R;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 干洗店服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:53
 */
public interface TGxdfwOrderService extends IOrderService {
	
	TGxdfwOrderEntity queryObject(Integer id);
	
	List<TGxdfwOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TGxdfwOrderEntity tGxdfwOrder);
	
	void update(TGxdfwOrderEntity tGxdfwOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 生成订单
	 * @param params
	 * @return
	 */
	R generateOrder(JSONObject params);
	/**
	 * 取消订单
	 * @param params
	 * @return
	 */
	R orderCancel(JSONObject params);

	int invalidOrderBatch(Integer[] array);
}
