package com.lcworld.dao;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TNoticeTypeEntity;

/**
 * 公告分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
public interface TNoticeTypeDao extends BaseDao<TNoticeTypeEntity> {
	/**
	 * 删除原有用户
	 * @param params
	 */
	void deleteUserType(JSONObject params);
	/**
	 * 添加用户选择
	 * @param params
	 */
	void addUserSelectedType(Map<String,Object> params);
	
}
