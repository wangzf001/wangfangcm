package com.lcworld.service;

import com.lcworld.entity.BgypfwCartEntity;

import java.util.List;
import java.util.Map;

/**
 * 办公用品服务-购物车
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public interface BgypfwCartService {
	
	BgypfwCartEntity queryObject(Integer id);
	
	List<BgypfwCartEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwCartEntity bgypfwCart);
	
	void update(BgypfwCartEntity bgypfwCart);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
