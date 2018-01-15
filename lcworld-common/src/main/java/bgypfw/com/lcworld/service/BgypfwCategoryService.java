package com.lcworld.service;

import com.lcworld.entity.BgypfwCategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
public interface BgypfwCategoryService {
	
	BgypfwCategoryEntity queryObject(Integer id);

	BgypfwCategoryEntity queryCategoryByName(Map<String,Object> params);
	
	List<BgypfwCategoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwCategoryEntity bgypfwCategory);
	
	void update(BgypfwCategoryEntity bgypfwCategory);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
