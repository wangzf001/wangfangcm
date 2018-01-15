package com.lcworld.dao;

import com.lcworld.entity.TYytcMealEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
public interface TYytcMealDao extends BaseDao<TYytcMealEntity> {
	/**
	 * 修改推荐
	 * @param mIds
	 */
	void recommend(Integer[] mIds);
	
}
