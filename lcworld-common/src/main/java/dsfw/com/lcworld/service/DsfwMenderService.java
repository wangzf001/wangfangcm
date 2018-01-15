package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.DsfwMenderEntity;
import com.lcworld.entity.TBxwxMenderEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 订水服务人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-15 13:51:56
 */
public interface DsfwMenderService extends IOperationUserService<DsfwMenderEntity>{
	
	DsfwMenderEntity queryObject(Integer id);
	
	List<DsfwMenderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int queryCommentTotal(Query query);
	
	void save(DsfwMenderEntity dsfwMender);
	
	void update(DsfwMenderEntity dsfwMender);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	 /**
	 * 评价列表
	 * @param user
	 */
	List<?> CommentList(Query q);
	/**
	 * 评价详情
	 * @param user
	 */
	<T> T CommentDetail(JSONObject params);
}
