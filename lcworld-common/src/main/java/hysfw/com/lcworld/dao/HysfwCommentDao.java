package com.lcworld.dao;

import java.util.List;

import com.lcworld.entity.HysfwCommentEntity;
import com.lcworld.utils.Query;

/**
 * 报修维修评论表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
public interface HysfwCommentDao extends BaseDao<HysfwCommentEntity> {
	
	List<?> queryListByScoretype(Query q);
	
    int queryCommentTotal(Query query);
}
