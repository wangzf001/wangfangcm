package com.lcworld.dao;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.BgypfwSkuidEntity;

/**
 * 办公用品服务-规格
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
public interface BgypfwSkuidDao extends BaseDao<BgypfwSkuidEntity> {

	Integer queryProductRemain(Map<String, Object> params1);

//	void updateSaleStatus(Map<String, Object> params);

	void deleteByPids(Integer[] ids);
	/**
	 * 查询商品价格区间
	 * @param params
	 * @return
	 */
	Map<String, Object> queryPricerange(Map<String, Object> params);

	BgypfwSkuidEntity querySkuidByName(String skuidName);

}
