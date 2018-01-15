package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.BgypfwMenderEntity;
import com.lcworld.utils.Query;

/**
 * 维修人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-12 17:02:33
 */
public interface BgypfwMenderService extends IOperationUserService<BgypfwMenderEntity> {
	
	BgypfwMenderEntity queryObject(Integer id);
	
	List<BgypfwMenderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwMenderEntity bgypfwMender);
	
	void update(BgypfwMenderEntity bgypfwMender);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<BgypfwMenderEntity> queryMenderlist(Map<String, Object> query);
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
