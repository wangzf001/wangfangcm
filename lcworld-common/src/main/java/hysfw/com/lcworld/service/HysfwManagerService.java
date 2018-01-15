package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.BgypfwMenderEntity;
import com.lcworld.entity.HysfwManagerEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 理发服务-理发师
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-08 19:47:46
 */
public interface HysfwManagerService extends IOperationUserService<HysfwManagerEntity>{
	
	HysfwManagerEntity queryObject(Integer id);
	
	List<HysfwManagerEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);

	int queryCommentTotal(Query query);
	
	void save(HysfwManagerEntity hysfwManager);
	
	void update(HysfwManagerEntity hysfwManager);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<HysfwManagerEntity> queryMenderlist(Map<String, Object> query);
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
