package com.lcworld.service;

import com.lcworld.entity.TDcfwGetfoodtimeEntity;

import java.util.List;
import java.util.Map;

/**
 * 订餐服务-取餐时间
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
public interface TDcfwGetfoodtimeService {
	
	TDcfwGetfoodtimeEntity queryObject(Integer id);
	
	List<TDcfwGetfoodtimeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TDcfwGetfoodtimeEntity tDcfwGetfoodtime);
	
	void update(TDcfwGetfoodtimeEntity tDcfwGetfoodtime);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
