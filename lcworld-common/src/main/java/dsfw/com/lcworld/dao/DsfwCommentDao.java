package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.DsfwCommentEntity;
import com.lcworld.utils.Query;

/**
 * 订水服务-评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:22
 */
public interface DsfwCommentDao extends BaseDao<DsfwCommentEntity> {

	void addImgList(Map<String, Object> paramMap);
	
	List<?> queryListByScoretype(Query q);

    int queryCommentTotal(Query query);
}
