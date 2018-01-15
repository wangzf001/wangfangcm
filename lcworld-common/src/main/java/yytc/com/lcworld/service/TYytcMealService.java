package com.lcworld.service;

import com.lcworld.entity.TYytcMealEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
public interface TYytcMealService {
	
	TYytcMealEntity queryObject(Integer mId);
	
	List<TYytcMealEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TYytcMealEntity tYytcMeal);
	
	void update(TYytcMealEntity tYytcMeal);
	
	void delete(Integer mId);
	
	void deleteBatch(Integer[] mIds);
	/**
	 * 批量保存
	 * @param list
	 */
	void saveBatch(List<TYytcMealEntity> list);
	/**
	 * 修改推荐
	 * @param mIds
	 */
	void recommend(Integer[] mIds);
}
