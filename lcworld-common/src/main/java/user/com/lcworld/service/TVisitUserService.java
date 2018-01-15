package com.lcworld.service;

import com.lcworld.entity.TVisitUserEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 来访人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 14:23:43
 */
public interface TVisitUserService extends IOrderService{
	
	TVisitUserEntity queryObject(Integer id);
	
	List<TVisitUserEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TVisitUserEntity tVisitUser);
	
	void update(TVisitUserEntity tVisitUser);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<TVisitUserEntity> queryOrderList(Query q);
}
