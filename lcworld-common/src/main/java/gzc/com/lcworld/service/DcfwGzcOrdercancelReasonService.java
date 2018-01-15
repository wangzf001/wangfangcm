package com.lcworld.service;

import com.lcworld.entity.DcfwGzcOrdercancelReasonEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 13:35:12
 */
public interface DcfwGzcOrdercancelReasonService {
	
	DcfwGzcOrdercancelReasonEntity queryObject(Integer id);
	
	List<DcfwGzcOrdercancelReasonEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DcfwGzcOrdercancelReasonEntity dcfwGzcOrdercancelReason);
	
	void update(DcfwGzcOrdercancelReasonEntity dcfwGzcOrdercancelReason);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
