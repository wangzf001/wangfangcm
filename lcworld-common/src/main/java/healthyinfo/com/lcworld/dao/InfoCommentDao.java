package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.InfoCommentEntity;
import com.lcworld.utils.Query;

/**
 * 资讯评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 15:14:38
 */
public interface InfoCommentDao extends BaseDao<InfoCommentEntity> {

    List<Map<String, Object>> querycommentList(Query query);

    int querycommentTotal(Query query);
	
}
