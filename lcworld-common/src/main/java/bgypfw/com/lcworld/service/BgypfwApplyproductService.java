package com.lcworld.service;

import com.lcworld.entity.BgypfwApplyproductEntity;

import java.util.List;
import java.util.Map;

/**
 * 办公用品服务-申请商品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public interface BgypfwApplyproductService {
	
	BgypfwApplyproductEntity queryObject(Integer id);
	
	List<BgypfwApplyproductEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwApplyproductEntity bgypfwApplyproduct);
	
	void update(BgypfwApplyproductEntity bgypfwApplyproduct);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
