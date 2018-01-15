package com.lcworld.service;

import com.lcworld.entity.TUserAdviceEntity;

import java.util.List;
import java.util.Map;

/**
 * 意见反馈
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 14:23:43
 */
public interface TUserAdviceService {
	
	TUserAdviceEntity queryObject(Integer id);
	
	List<TUserAdviceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TUserAdviceEntity tUserAdvice);
	
	void update(TUserAdviceEntity tUserAdvice);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
