package com.lcworld.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.lcworld.entity.HysfwCommentEntity;
import com.lcworld.utils.R;

/**
 * 报修维修评论表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
public interface HysfwCommentService {

	HysfwCommentEntity queryObject(Integer id);

	List<HysfwCommentEntity> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(HysfwCommentEntity hysfwComment);

	void update(HysfwCommentEntity hysfwComment);

	void delete(Integer id);

	void deleteBatch(Integer[] ids);

	R addComment(HysfwCommentEntity commentEntity, MultipartFile[] imgFile)
			throws IOException;
}
