package com.lcworld.dao;

import java.util.HashMap;

import com.lcworld.entity.TDcfwFoodEntity;

/**
 * 订餐系统-食物
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
public interface TDcfwFoodDao extends BaseDao<TDcfwFoodEntity> {
	/**
	 * 取消订单时恢复食物的数量
	 * @param param
	 */
	void recoverCount(HashMap<String, Object> param);
	
}
