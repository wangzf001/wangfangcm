package com.lcworld.service;

import com.lcworld.entity.OfficeEntity;

import java.util.List;
import java.util.Map;

/**
 * 处室
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-11 18:37:02
 */
public interface OfficeService {
	
	OfficeEntity queryObject(Integer id);
	
	List<OfficeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OfficeEntity office);
	
	void update(OfficeEntity office);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
