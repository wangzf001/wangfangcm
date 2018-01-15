package com.lcworld.service;

import com.lcworld.entity.BgypfwProducpackSpecEntity;

import java.util.List;
import java.util.Map;

/**
 * 办公用品服务-包装规格表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-20 20:10:15
 */
public interface BgypfwProducpackSpecService {
	
	BgypfwProducpackSpecEntity queryObject(Integer id);
	
	List<BgypfwProducpackSpecEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwProducpackSpecEntity bgypfwProducpackSpec);
	
	void update(BgypfwProducpackSpecEntity bgypfwProducpackSpec);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
