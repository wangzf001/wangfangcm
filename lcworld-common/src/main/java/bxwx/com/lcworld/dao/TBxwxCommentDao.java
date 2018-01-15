package com.lcworld.dao;

import java.util.List;

import com.lcworld.entity.TBxwxCommentEntity;
import com.lcworld.utils.Query;

/**
 * 报修维修评论表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
public interface TBxwxCommentDao extends BaseDao<TBxwxCommentEntity> {

    Double queryScore(Integer menderid);
	
    List<?> queryListByScoretype(Query q);
    
    int queryCommentTotal(Query query);
}
