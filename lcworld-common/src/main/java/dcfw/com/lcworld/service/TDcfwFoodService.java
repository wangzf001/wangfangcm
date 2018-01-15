package com.lcworld.service;

import com.lcworld.entity.TDcfwFoodEntity;

import java.util.List;
import java.util.Map;

/**
 * 订餐系统-食物
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
public interface TDcfwFoodService extends ExcelService<TDcfwFoodEntity>{
	
	TDcfwFoodEntity queryObject(Integer id);
	
	List<TDcfwFoodEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TDcfwFoodEntity tDcfwFood);
	
	void update(TDcfwFoodEntity tDcfwFood);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 批量保存
	 * @param list
	 */
	void saveBatch(List<TDcfwFoodEntity> list);
}
