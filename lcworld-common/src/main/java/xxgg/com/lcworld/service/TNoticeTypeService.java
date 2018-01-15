package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TNoticeTypeEntity;
import com.lcworld.utils.R;

import java.util.List;
import java.util.Map;

/**
 * 公告分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
public interface TNoticeTypeService {
	
	TNoticeTypeEntity queryObject(Integer id);
	
	List<TNoticeTypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TNoticeTypeEntity tNoticeType);
	
	void update(TNoticeTypeEntity tNoticeType);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	R updateUserType(JSONObject params);
}
