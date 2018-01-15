package com.lcworld.dao;

import java.util.Map;

import com.lcworld.entity.DcfwGzcCommentEntity;

/**
 * 订餐服务评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 13:35:13
 */
public interface DcfwGzcCommentDao extends BaseDao<DcfwGzcCommentEntity> {

	void addImgList(Map<String, Object> paramMap);
	
}
