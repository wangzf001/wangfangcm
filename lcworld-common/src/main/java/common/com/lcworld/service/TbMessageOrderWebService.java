package com.lcworld.service;

import com.lcworld.entity.TbMessageOrderWebEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-26 15:12:11
 */
public interface TbMessageOrderWebService {
	
	TbMessageOrderWebEntity queryObject(Long autoId);
	
	List<TbMessageOrderWebEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TbMessageOrderWebEntity tbMessageOrderWeb);
	
	void update(TbMessageOrderWebEntity tbMessageOrderWeb);
	
	void delete(Long autoId);
	
	void deleteBatch(Long[] autoIds);
}
