package com.lcworld.dao;

import java.util.List;

import com.lcworld.dto.LffwCommentDTO;
import com.lcworld.entity.LffwCommentEntity;
import com.lcworld.utils.Query;

/**
 * 理发服务评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
public interface LffwCommentDao extends BaseDao<LffwCommentEntity> {

	Double queryScore(Integer barberid);

	List<LffwCommentDTO> queryCommentList(Query q);

	LffwCommentDTO queryLffwComment(Integer id);
	
}
