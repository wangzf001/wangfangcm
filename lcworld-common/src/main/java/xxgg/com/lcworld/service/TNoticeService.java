package com.lcworld.service;

import com.lcworld.entity.TNoticeEntity;

import java.util.List;
import java.util.Map;

/**
 * 公告
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
public interface TNoticeService extends ICollectionService<TNoticeEntity>{
	
	TNoticeEntity queryObject(Integer id);
	
	List<TNoticeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TNoticeEntity tNotice);
	
	void update(TNoticeEntity tNotice);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
