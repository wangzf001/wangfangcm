package com.lcworld.service;

import com.lcworld.entity.BgypfwProducbrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 办公用品服务-商品品牌分类表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-20 20:10:14
 */
public interface BgypfwProducbrandService {
	
	BgypfwProducbrandEntity queryObject(Integer id);
	
	List<BgypfwProducbrandEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwProducbrandEntity bgypfwProducbrand);
	
	void update(BgypfwProducbrandEntity bgypfwProducbrand);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
