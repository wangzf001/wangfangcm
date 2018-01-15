package com.lcworld.service;

import com.lcworld.entity.TsjyfwBookFavorcounviewEntity;

import java.util.List;
import java.util.Map;

/**
 * VIEW
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:24
 */
public interface TsjyfwBookFavorcounviewService {
	
	TsjyfwBookFavorcounviewEntity queryObject(Integer bookid);
	
	List<TsjyfwBookFavorcounviewEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TsjyfwBookFavorcounviewEntity tsjyfwBookFavorcounview);
	
	void update(TsjyfwBookFavorcounviewEntity tsjyfwBookFavorcounview);
	
	void delete(Integer bookid);
	
	void deleteBatch(Integer[] bookids);
}
