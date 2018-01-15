package com.lcworld.service;

import com.lcworld.entity.DsfwOrdercancelReasonEntity;

import java.util.List;
import java.util.Map;

/**
 * 订水服务-取消订单原因
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:20
 */
public interface DsfwOrdercancelReasonService {
	
	DsfwOrdercancelReasonEntity queryObject(Integer id);
	
	List<DsfwOrdercancelReasonEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DsfwOrdercancelReasonEntity dsfwOrdercancelReason);
	
	void update(DsfwOrdercancelReasonEntity dsfwOrdercancelReason);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
