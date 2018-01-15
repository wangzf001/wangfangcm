package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TBxwxMenderEntity;
import com.lcworld.utils.Query;

/**
 * 维修人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-14 11:50:03
 */
public interface TBxwxMenderService extends IOperationUserService<TBxwxMenderEntity>{
	
	TBxwxMenderEntity queryObject(Integer id);
	
	List<TBxwxMenderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	int queryCommentTotal(Query query);
	
	int quer(Map<String, Object> map);
	
	void save(TBxwxMenderEntity tBxwxMender);
	
	void update(TBxwxMenderEntity tBxwxMender);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    /**报修人员列表
     * @param query
     * @return
     */
    List<TBxwxMenderEntity> queryMenderlist(Map<String,Object> query);
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
