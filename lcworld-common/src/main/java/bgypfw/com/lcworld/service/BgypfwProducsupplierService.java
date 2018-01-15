package com.lcworld.service;

import com.lcworld.entity.BgypfwProducsupplierEntity;

import java.util.List;
import java.util.Map;

/**
 * 办公用品服务-商品供应商信息表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-20 20:10:14
 */
public interface BgypfwProducsupplierService {
	
	BgypfwProducsupplierEntity queryObject(Integer id);
	
	List<BgypfwProducsupplierEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwProducsupplierEntity bgypfwProducsupplier);
	
	void update(BgypfwProducsupplierEntity bgypfwProducsupplier);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
