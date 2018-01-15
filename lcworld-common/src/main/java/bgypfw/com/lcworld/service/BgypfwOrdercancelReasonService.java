package com.lcworld.service;

import com.lcworld.entity.BgypfwOrdercancelReasonEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 10:39:19
 */
public interface BgypfwOrdercancelReasonService {
	
	BgypfwOrdercancelReasonEntity queryObject(Integer id);
	
	List<BgypfwOrdercancelReasonEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwOrdercancelReasonEntity bgypfwOrdercancelReason);
	
	void update(BgypfwOrdercancelReasonEntity bgypfwOrdercancelReason);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
