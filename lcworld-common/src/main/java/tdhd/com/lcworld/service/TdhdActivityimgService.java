package com.lcworld.service;

import com.lcworld.entity.TdhdActivityimgEntity;

import java.util.List;
import java.util.Map;

/**
 * 团队活动系统-活动配图
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 13:45:52
 */
public interface TdhdActivityimgService {
	
	TdhdActivityimgEntity queryObject(Integer aiId);
	
	List<TdhdActivityimgEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TdhdActivityimgEntity tdhdActivityimg);
	
	void update(TdhdActivityimgEntity tdhdActivityimg);
	
	void delete(Integer aiId);
	
	void deleteBatch(Integer[] aiIds);
}
