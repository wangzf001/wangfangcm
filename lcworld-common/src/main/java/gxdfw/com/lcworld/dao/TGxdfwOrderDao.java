package com.lcworld.dao;

import java.util.HashMap;

import com.lcworld.entity.TGxdfwOrderEntity;

/**
 * 干洗店服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:53
 */
public interface TGxdfwOrderDao extends BaseDao<TGxdfwOrderEntity> {

	int invalidOrderBatch(HashMap<String, Object> params);
	
}
