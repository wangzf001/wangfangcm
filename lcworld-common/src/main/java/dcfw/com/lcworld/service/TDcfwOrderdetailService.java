package com.lcworld.service;

import com.lcworld.entity.TDcfwOrderdetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 订餐服务订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
public interface TDcfwOrderdetailService {
	
	TDcfwOrderdetailEntity queryObject(Integer id);
	
	List<TDcfwOrderdetailEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TDcfwOrderdetailEntity tDcfwOrderdetail);
	
	void update(TDcfwOrderdetailEntity tDcfwOrderdetail);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
