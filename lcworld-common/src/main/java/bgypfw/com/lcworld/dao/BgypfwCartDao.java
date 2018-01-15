package com.lcworld.dao;

import java.util.Map;

import com.lcworld.entity.BgypfwCartEntity;

/**
 * 办公用品服务-购物车
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public interface BgypfwCartDao extends BaseDao<BgypfwCartEntity> {
	/**
	 * 批量删除购物车表中已经下过单的购物车项
	 * @param paramCart
	 */
	void deleteBatchBySkuids(Map<String, Object> paramCart);
	
}
