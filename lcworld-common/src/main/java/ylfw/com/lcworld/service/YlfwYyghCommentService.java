package com.lcworld.service;

import com.lcworld.entity.YlfwYyghCommentEntity;
import com.lcworld.entity.YlfwYyghOrderEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 医疗服务预约挂号评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:52
 */
public interface YlfwYyghCommentService {
	
	YlfwYyghCommentEntity queryObject(Integer id);
	
	List<YlfwYyghCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwYyghCommentEntity ylfwYyghComment);
	
	void update(YlfwYyghCommentEntity ylfwYyghComment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void savecomment(YlfwYyghCommentEntity comment, YlfwYyghOrderEntity order);

    List<com.lcworld.dto.YlfwCommentDTO> queryCommentList(Query q);
}
