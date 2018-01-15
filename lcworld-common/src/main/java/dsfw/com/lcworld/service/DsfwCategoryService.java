package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.DsfwCategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 订水服务-分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:18
 */
public interface DsfwCategoryService {
	
	DsfwCategoryEntity queryObject(Integer id);
	
	List<DsfwCategoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DsfwCategoryEntity dsfwCategory);
	
	void update(DsfwCategoryEntity dsfwCategory);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	DsfwCategoryEntity queryBygid(JSONObject params);
}
