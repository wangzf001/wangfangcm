package com.lcworld.service;

import com.lcworld.entity.BgypfwProductEntity;

import java.util.List;
import java.util.Map;

/**
 * 办公用品服务-商品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
public interface BgypfwProductService extends ICollectionService<BgypfwProductEntity>,ExcelService<BgypfwProductEntity> {
	
	BgypfwProductEntity queryObject(Integer id);

	BgypfwProductEntity queryProductByName(String productname);

	List<BgypfwProductEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwProductEntity bgypfwProduct);

	void saveBatch(List<BgypfwProductEntity> list);
	
	void update(BgypfwProductEntity bgypfwProduct);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	void update1(BgypfwProductEntity product);
}
