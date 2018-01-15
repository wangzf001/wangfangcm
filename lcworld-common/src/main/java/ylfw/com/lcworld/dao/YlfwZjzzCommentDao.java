package com.lcworld.dao;

import java.util.List;

import com.lcworld.dto.YlfwCommentDTO;
import com.lcworld.entity.YlfwZjzzCommentEntity;
import com.lcworld.utils.Query;

/**
 * 医疗服务专家坐诊评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
public interface YlfwZjzzCommentDao extends BaseDao<YlfwZjzzCommentEntity> {

    List<YlfwCommentDTO> queryCommentList(Query q);

    Double getScore(Integer doctorid);
	
}
