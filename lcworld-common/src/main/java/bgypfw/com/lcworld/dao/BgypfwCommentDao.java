package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.BgypfwCommentEntity;
import com.lcworld.utils.Query;

/**
 * 办公用品服务-评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public interface BgypfwCommentDao extends BaseDao<BgypfwCommentEntity> {

	Integer queryStarsCountWithin(Map<String, Object> map);
	/**
	 * 添加图片
	 * @param paramMap
	 */
	void addImgList(Map<String, Object> paramMap);

	List<?> queryListByScoretype(Query q);
	
	int queryCommentTotal(Query query);
}
