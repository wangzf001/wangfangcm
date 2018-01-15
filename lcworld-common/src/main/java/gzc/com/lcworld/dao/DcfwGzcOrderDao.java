package com.lcworld.dao;

import java.util.HashMap;
import java.util.Map;

import com.lcworld.entity.DcfwGzcOrderEntity;

/**
 * 订餐服务-工作餐订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 13:35:13
 */
public interface DcfwGzcOrderDao extends BaseDao<DcfwGzcOrderEntity> {

	int updateOrderBatch(HashMap<String, Object> params);

}
