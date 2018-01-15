package com.lcworld.service;

import com.lcworld.entity.TGxdfwOrdercancelReasonEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 15:13:16
 */
public interface TGxdfwOrdercancelReasonService {
	
	TGxdfwOrdercancelReasonEntity queryObject(Integer id);
	
	List<TGxdfwOrdercancelReasonEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TGxdfwOrdercancelReasonEntity tGxdfwOrdercancelReason);
	
	void update(TGxdfwOrdercancelReasonEntity tGxdfwOrdercancelReason);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
