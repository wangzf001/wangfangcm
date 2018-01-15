package com.lcworld.dao;

import java.util.List;

import com.lcworld.dto.YlfwCommentDTO;
import com.lcworld.entity.YlfwYyghCommentEntity;
import com.lcworld.utils.Query;

/**
 * 医疗服务预约挂号评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:52
 */
public interface YlfwYyghCommentDao extends BaseDao<YlfwYyghCommentEntity> {

    Double getScore(Integer doctorid);

    List<YlfwCommentDTO> queryCommentList(Query q);
	
}
