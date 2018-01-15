package com.lcworld.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.lcworld.entity.TGxdfwCommentEntity;
import com.lcworld.utils.R;

/**
 * 干洗店服务评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:54
 */
public interface TGxdfwCommentService {

	TGxdfwCommentEntity queryObject(Integer id);

	List<TGxdfwCommentEntity> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(TGxdfwCommentEntity tGxdfwComment);

	void update(TGxdfwCommentEntity tGxdfwComment);

	void delete(Integer id);

	void deleteBatch(Integer[] ids);

	/**
	 * 添加评论
	 * 
	 * @param commentEntity
	 * @param imgFile
	 * @return
	 * @throws Exception
	 */
	R addComment(TGxdfwCommentEntity commentEntity, MultipartFile[] imgFile)
			throws Exception;

	/**
	 * 获取指定分数区间的评论数
	 * 
	 * @param query
	 */
	int queryStarsCountWithin(Map<String, Object> map);
}
