package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.UserCfEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-09 15:27:08
 */
public interface UserCfService {
	
	UserCfEntity queryObject(Integer id);
	
	List<UserCfEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(UserCfEntity userCf);
	
	void update(UserCfEntity userCf);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 条件查询用户餐费信息
	 * @param params
	 * @return
	 */
	UserCfEntity queryByCondition(Map<String,Object> params);
}
