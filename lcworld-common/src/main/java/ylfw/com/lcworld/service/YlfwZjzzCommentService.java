package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.lcworld.dto.YlfwCommentDTO;
import com.lcworld.entity.YlfwZjzzCommentEntity;
import com.lcworld.entity.YlfwZjzzOrderEntity;
import com.lcworld.utils.Query;

/**
 * 医疗服务专家坐诊评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
public interface YlfwZjzzCommentService {
	
	YlfwZjzzCommentEntity queryObject(Integer id);
	
	List<YlfwZjzzCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwZjzzCommentEntity ylfwZjzzComment);
	
	void update(YlfwZjzzCommentEntity ylfwZjzzComment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<YlfwCommentDTO> queryCommentList(Query q);

    void savecomment(YlfwZjzzCommentEntity comment, YlfwZjzzOrderEntity order);
}
