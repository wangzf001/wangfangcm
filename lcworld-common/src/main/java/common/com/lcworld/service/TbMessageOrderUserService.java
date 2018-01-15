package com.lcworld.service;

import com.lcworld.entity.TbMessageOrderUserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-26 15:12:11
 */
public interface TbMessageOrderUserService {
	
	TbMessageOrderUserEntity queryObject(Long autoId);
	
	List<TbMessageOrderUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TbMessageOrderUserEntity tbMessageOrderUser);
	
	void update(TbMessageOrderUserEntity tbMessageOrderUser);
	
	void delete(Long autoId);
	
	void deleteBatch(Long[] autoIds);
}
