package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TsjyUsersearchhistoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 图书借阅用户搜索记录表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-23 16:57:00
 */
public interface TsjyUsersearchhistoryService {
	
	TsjyUsersearchhistoryEntity queryObject(Integer id);
	
	List<TsjyUsersearchhistoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TsjyUsersearchhistoryEntity tsjyUsersearchhistory);
	
	void update(TsjyUsersearchhistoryEntity tsjyUsersearchhistory);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void savehistory(JSONObject params);

    void deleteByWhere(JSONObject params);
}
