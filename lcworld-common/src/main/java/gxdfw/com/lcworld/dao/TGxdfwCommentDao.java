package com.lcworld.dao;

import java.util.Map;

import com.lcworld.entity.TGxdfwCommentEntity;

/**
 * 干洗店服务评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:54
 */
public interface TGxdfwCommentDao extends BaseDao<TGxdfwCommentEntity> {

	void addImgList(Map<String, Object> paramMap);

	int queryStarsCountWithin(Map<String, Object> map);
	
}
