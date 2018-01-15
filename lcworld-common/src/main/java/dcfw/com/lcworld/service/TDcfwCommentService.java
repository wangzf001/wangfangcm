package com.lcworld.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.lcworld.entity.TDcfwCommentEntity;
import com.lcworld.utils.R;

/**
 * 订餐服务评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
public interface TDcfwCommentService {

	TDcfwCommentEntity queryObject(Integer id);

	List<TDcfwCommentEntity> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	void save(TDcfwCommentEntity tDcfwComment);

	void update(TDcfwCommentEntity tDcfwComment);

	void delete(Integer id);

	void deleteBatch(Integer[] ids);

	/**
	 * 添加评论
	 * 
	 * @param commentEntity
	 * @param imgFile
	 * @return
	 * @throws IOException
	 */
	R addComment(TDcfwCommentEntity commentEntity, MultipartFile[] imgFile)
			throws IOException;
}
