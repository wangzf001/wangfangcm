package com.lcworld.service;

import com.lcworld.entity.UserAdviceEntity;

import java.util.List;
import java.util.Map;

/**
 * 意见反馈
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-10 16:01:16
 */
public interface UserAdviceService {
	
	UserAdviceEntity queryObject(Integer id);
	
	List<UserAdviceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserAdviceEntity userAdvice);
	
	void update(UserAdviceEntity userAdvice);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
