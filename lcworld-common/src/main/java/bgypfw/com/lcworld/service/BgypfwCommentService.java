package com.lcworld.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.lcworld.entity.BgypfwCommentEntity;
import com.lcworld.utils.R;

/**
 * 办公用品服务-评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public interface BgypfwCommentService extends CommonOrderService{
	
	BgypfwCommentEntity queryObject(Integer id);
	
	List<BgypfwCommentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwCommentEntity bgypfwComment);
	
	void update(BgypfwCommentEntity bgypfwComment);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	/**
	 * 好评率查询
	 * @param query
	 */
	Double queryRateHighStars(Map<String, Object> map);
	/**
	 * 添加评论
	 * @param commentEntity
	 * @param imgFile
	 * @return
	 */
	R addComment(BgypfwCommentEntity commentEntity, MultipartFile[] imgFile) throws IOException;
	/**
	 * 查询不同分段的数量
	 * @param paramMap
	 * @return
	 */
	int queryStarsCountWithin(Map<String, Object> map);
}
