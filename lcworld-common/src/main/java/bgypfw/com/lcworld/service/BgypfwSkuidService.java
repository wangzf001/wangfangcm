package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.BgypfwSkuidEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 办公用品服务-规格
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
public interface BgypfwSkuidService {
	
	BgypfwSkuidEntity queryObject(Integer id);

	BgypfwSkuidEntity querySkuidByName(String skuidName);

	List<BgypfwSkuidEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwSkuidEntity bgypfwSkuid);
	
	void update(BgypfwSkuidEntity bgypfwSkuid);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 查询商品剩余总库存
	 * @param params1
	 * @return
	 */
	Integer queryProductRemain(Map<String, Object> params1);
	/**
	 * 更新商品上架状态
	 * @param ids
	 * @param status
	 */
//	void updateSaleStatus(JSONObject params);

	Map<String, Object> queryPricerange(Map<String, Object> params);

	void savesku(BgypfwSkuidEntity bgypfwSkuid);

	void updatesku(BgypfwSkuidEntity bgypfwSkuid);

	void deletesku(Integer[] ids);
}
