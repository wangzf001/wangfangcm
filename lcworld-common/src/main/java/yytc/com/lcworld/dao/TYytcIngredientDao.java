package com.lcworld.dao;

import com.lcworld.entity.TYytcIngredientEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
public interface TYytcIngredientDao extends BaseDao<TYytcIngredientEntity> {
	/**
	 * 删除菜单下所有原料
	 * @param mIds
	 */
	void deleteByMids(Integer[] mIds);
	
}
