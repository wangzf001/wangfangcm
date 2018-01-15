package com.lcworld.service;

import com.lcworld.entity.TNoticeSetEntity;

import java.util.List;
import java.util.Map;

/**
 * 消息设置
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
public interface TNoticeSetService {
	
	TNoticeSetEntity queryObject(Integer id);
	
	List<TNoticeSetEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TNoticeSetEntity tNoticeSet);
	
	void update(TNoticeSetEntity tNoticeSet);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
