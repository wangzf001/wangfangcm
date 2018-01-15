package com.lcworld.service;

import com.lcworld.entity.TYytcCommentEntity;

import java.util.List;
import java.util.Map;

/**
 * 营养套餐评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
public interface TYytcCommentService {
	
	TYytcCommentEntity queryObject(Integer id);
	
	List<TYytcCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TYytcCommentEntity tYytcComment);
	
	void update(TYytcCommentEntity tYytcComment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
