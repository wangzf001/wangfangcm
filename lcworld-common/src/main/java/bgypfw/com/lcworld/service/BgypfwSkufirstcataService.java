package com.lcworld.service;

import com.lcworld.entity.BgypfwSkufirstcataEntity;

import java.util.List;
import java.util.Map;

/**
 * 办公用品服务-规格分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
public interface BgypfwSkufirstcataService {
	
	BgypfwSkufirstcataEntity queryObject(Integer id);
	
	List<BgypfwSkufirstcataEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwSkufirstcataEntity bgypfwSkufirstcata);
	
	void update(BgypfwSkufirstcataEntity bgypfwSkufirstcata);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
