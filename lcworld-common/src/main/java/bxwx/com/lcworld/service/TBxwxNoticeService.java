package com.lcworld.service;

import com.lcworld.entity.TBxwxNoticeEntity;

import java.util.List;
import java.util.Map;

/**
 * 维修消息
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-14 14:21:50
 */
public interface TBxwxNoticeService extends TalkService{
	
	TBxwxNoticeEntity queryObject(Integer id);
	
	List<TBxwxNoticeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TBxwxNoticeEntity tBxwxNotice);
	
	void update(TBxwxNoticeEntity tBxwxNotice);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
