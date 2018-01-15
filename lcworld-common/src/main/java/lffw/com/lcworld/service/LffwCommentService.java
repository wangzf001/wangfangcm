package com.lcworld.service;

import com.lcworld.dto.LffwCommentDTO;
import com.lcworld.entity.LffwCommentEntity;
import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 理发服务评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
public interface LffwCommentService {
	
	LffwCommentEntity queryObject(Integer id);
	
	List<LffwCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwCommentEntity lffwComment);
	
	void update(LffwCommentEntity lffwComment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void savecomment(LffwCommentEntity comment, LffwOrderEntity order);

   List<LffwCommentDTO> queryCommentList(Query q);

    LffwCommentDTO queryLffwComment(Integer id);
}
