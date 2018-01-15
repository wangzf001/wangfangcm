package com.lcworld.dao;

import com.lcworld.entity.TsjyfwCommentEntity;

/**
 * 图书借阅服务-评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
public interface TsjyfwCommentDao extends BaseDao<TsjyfwCommentEntity> {

	Double queryScore(Integer bookid);

}
