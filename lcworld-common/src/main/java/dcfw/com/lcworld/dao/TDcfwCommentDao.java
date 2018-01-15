package com.lcworld.dao;

import java.util.Map;

import com.lcworld.entity.TDcfwCommentEntity;

/**
 * 订餐服务评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
public interface TDcfwCommentDao extends BaseDao<TDcfwCommentEntity> {

	void addImgList(Map<String, Object> paramMap);

}
