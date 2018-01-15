package com.lcworld.service;

import com.lcworld.entity.TNoticeUserinterestEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户感兴趣公告分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
public interface TNoticeUserinterestService {
	
	TNoticeUserinterestEntity queryObject(Integer id);
	
	List<TNoticeUserinterestEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TNoticeUserinterestEntity tNoticeUserinterest);
	
	void update(TNoticeUserinterestEntity tNoticeUserinterest);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
