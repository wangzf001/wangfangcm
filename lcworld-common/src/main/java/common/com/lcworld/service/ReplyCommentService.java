package com.lcworld.service;

import com.lcworld.entity.ReplyCommentEntity;

import java.util.List;
import java.util.Map;

/**
 * 回复评论表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-20 13:29:16
 */
public interface ReplyCommentService {
	
	ReplyCommentEntity queryObject(Integer id);
	
	List<ReplyCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ReplyCommentEntity replyComment);
	
	void update(ReplyCommentEntity replyComment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
