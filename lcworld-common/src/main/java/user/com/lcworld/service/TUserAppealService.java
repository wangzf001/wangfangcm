package com.lcworld.service;

import com.lcworld.entity.TUserAppealEntity;

import java.util.List;
import java.util.Map;

/**
 * 账号申诉
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 14:23:42
 */
public interface TUserAppealService {
	
	TUserAppealEntity queryObject(Integer id);
	
	List<TUserAppealEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TUserAppealEntity tUserAppeal);
	
	void update(TUserAppealEntity tUserAppeal);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
