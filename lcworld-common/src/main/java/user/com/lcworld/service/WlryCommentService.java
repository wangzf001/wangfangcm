package com.lcworld.service;

import com.lcworld.entity.TVisitUserEntity;
import com.lcworld.entity.WlryCommentEntity;

import java.util.List;
import java.util.Map;

/**
 * 报修维修评论表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-09 11:05:54
 */
public interface WlryCommentService {
	
	WlryCommentEntity queryObject(Integer id);
	
	List<WlryCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WlryCommentEntity wlryComment);
	
	void update(WlryCommentEntity wlryComment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void savecomment(WlryCommentEntity comment, TVisitUserEntity order);
}
