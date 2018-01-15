package com.lcworld.service;

import com.lcworld.entity.TNoticeCommentEntity;

import java.util.List;
import java.util.Map;

/**
 * 公告评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
public interface TNoticeCommentService {
	
	TNoticeCommentEntity queryObject(Integer id);
	
	List<TNoticeCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TNoticeCommentEntity tNoticeComment);
	
	void update(TNoticeCommentEntity tNoticeComment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
