package com.lcworld.service;

import com.lcworld.entity.TsjyfwBooktypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 图书借阅服务--图书分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
public interface TsjyfwBooktypeService {
	
	TsjyfwBooktypeEntity queryObject(Integer id);
	
	List<TsjyfwBooktypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TsjyfwBooktypeEntity tsjyfwBooktype);
	
	void update(TsjyfwBooktypeEntity tsjyfwBooktype);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
