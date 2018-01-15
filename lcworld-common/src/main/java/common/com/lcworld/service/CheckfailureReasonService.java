package com.lcworld.service;

import com.lcworld.entity.CheckfailureReasonEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-11 14:58:34
 */
public interface CheckfailureReasonService {
	
	CheckfailureReasonEntity queryObject(Integer id);
	
	List<CheckfailureReasonEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CheckfailureReasonEntity checkfailureReason);
	
	void update(CheckfailureReasonEntity checkfailureReason);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
