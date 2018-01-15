package com.lcworld.service;

import com.lcworld.entity.TYytcIngredientEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
public interface TYytcIngredientService {
	
	TYytcIngredientEntity queryObject(Integer iId);
	
	List<TYytcIngredientEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TYytcIngredientEntity tYytcIngredient);
	
	void update(TYytcIngredientEntity tYytcIngredient);
	
	void delete(Integer iId);
	
	void deleteBatch(Integer[] iIds);
}
