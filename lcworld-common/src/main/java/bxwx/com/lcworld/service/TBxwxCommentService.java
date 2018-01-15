package com.lcworld.service;

import com.lcworld.entity.TBxwxCommentEntity;
import com.lcworld.entity.TBxwxOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 报修维修评论表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
public interface TBxwxCommentService {
	
	TBxwxCommentEntity queryObject(Integer id);
	
	List<TBxwxCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TBxwxCommentEntity tBxwxComment);
	
	void update(TBxwxCommentEntity tBxwxComment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void savecomment(TBxwxCommentEntity comment, TBxwxOrderEntity order);
    

}
