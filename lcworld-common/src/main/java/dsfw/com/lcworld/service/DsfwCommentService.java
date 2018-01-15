package com.lcworld.service;

import com.lcworld.entity.DsfwCommentEntity;
import com.lcworld.utils.R;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 订水服务-评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:22
 */
public interface DsfwCommentService {
	
	DsfwCommentEntity queryObject(Integer id);
	
	List<DsfwCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DsfwCommentEntity dsfwComment);
	
	void update(DsfwCommentEntity dsfwComment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 添加评论
	 * @param commentEntity
	 * @param imgFile
	 * @return
	 * @throws IOException 
	 */
	R addComment(DsfwCommentEntity commentEntity, MultipartFile[] imgFile) throws IOException;
}
