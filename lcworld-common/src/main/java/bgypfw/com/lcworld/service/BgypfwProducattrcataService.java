package com.lcworld.service;

import com.lcworld.entity.BgypfwProducattrcataEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-28 11:43:37
 */
public interface BgypfwProducattrcataService {
	
	BgypfwProducattrcataEntity queryObject(Integer id);
	
	List<BgypfwProducattrcataEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwProducattrcataEntity bgypfwProducattrcata);
	
	void update(BgypfwProducattrcataEntity bgypfwProducattrcata);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);


}
