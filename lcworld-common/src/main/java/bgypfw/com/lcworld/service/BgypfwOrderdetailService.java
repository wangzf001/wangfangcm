package com.lcworld.service;

import com.lcworld.entity.BgypfwOrderdetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 办公用品服务-订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public interface BgypfwOrderdetailService {
	
	BgypfwOrderdetailEntity queryObject(Integer id);
	
	List<BgypfwOrderdetailEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwOrderdetailEntity bgypfwOrderdetail);
	
	void update(BgypfwOrderdetailEntity bgypfwOrderdetail);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
