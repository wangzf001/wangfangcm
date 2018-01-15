package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.DeliverymanEntity;
import com.lcworld.utils.Query;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-11 15:13:39
 */
public interface DeliverymanService extends IOperationUserService<DeliverymanEntity>{
	
	DeliverymanEntity queryObject(Integer id);
	
	List<DeliverymanEntity> queryList(Map<String, Object> map);

	List<DeliverymanEntity> queryDeliveryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);
	
	void save(DeliverymanEntity deliveryman);
	
	void update(DeliverymanEntity deliveryman);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	int queryCommentTotal(Query query);
	 /**
	 * 评价列表
	 * @param user
	 */
	List<?> CommentList(Query q);
}
