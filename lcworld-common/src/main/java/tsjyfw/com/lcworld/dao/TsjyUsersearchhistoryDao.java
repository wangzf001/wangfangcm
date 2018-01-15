package com.lcworld.dao;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TsjyUsersearchhistoryEntity;

/**
 * 图书借阅用户搜索记录表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-23 16:57:00
 */
public interface TsjyUsersearchhistoryDao extends BaseDao<TsjyUsersearchhistoryEntity> {

    void deleteByWhere(JSONObject params);
	
}
