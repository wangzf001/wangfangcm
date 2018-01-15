package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.TsjyfwCommentEntity;
import com.lcworld.entity.TsjyfwOrderEntity;

/**
 * 图书借阅服务-评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
public interface TsjyfwCommentService {
	
	TsjyfwCommentEntity queryObject(Integer id);
	
	List<TsjyfwCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TsjyfwCommentEntity tsjyfwComment);
	
	void update(TsjyfwCommentEntity tsjyfwComment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void savecomment(TsjyfwCommentEntity comment, TsjyfwOrderEntity order);
}
