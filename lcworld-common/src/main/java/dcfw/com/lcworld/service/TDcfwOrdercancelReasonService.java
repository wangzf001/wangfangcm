package com.lcworld.service;

import com.lcworld.entity.TDcfwOrdercancelReasonEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-14 14:32:20
 */
public interface TDcfwOrdercancelReasonService {
	
	TDcfwOrdercancelReasonEntity queryObject(Integer id);
	
	List<TDcfwOrdercancelReasonEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TDcfwOrdercancelReasonEntity tDcfwOrdercancelReason);
	
	void update(TDcfwOrdercancelReasonEntity tDcfwOrdercancelReason);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
