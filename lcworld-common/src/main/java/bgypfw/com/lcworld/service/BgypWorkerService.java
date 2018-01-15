package com.lcworld.service;

import com.lcworld.entity.BgypWorkerEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-08 16:15:29
 */
public interface BgypWorkerService {
	
	BgypWorkerEntity queryObject(Integer id);
	
	List<BgypWorkerEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypWorkerEntity bgypWorker);
	
	void update(BgypWorkerEntity bgypWorker);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
