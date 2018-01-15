package com.lcworld.dao;

import java.util.Map;

import com.lcworld.entity.DsfwCategoryEntity;

/**
 * 订水服务-分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:18
 */
public interface DsfwCategoryDao extends BaseDao<DsfwCategoryEntity> {

	DsfwCategoryEntity queryBygid(Map<String,Object> params);
	
}
