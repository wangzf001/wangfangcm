package com.lcworld.service;

import com.lcworld.entity.DsfwOrderdetailEntity;

import java.util.List;
import java.util.Map;

/**
 * 订水服务-订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:19
 */
public interface DsfwOrderdetailService {
	
	DsfwOrderdetailEntity queryObject(Integer id);
	
	List<DsfwOrderdetailEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DsfwOrderdetailEntity dsfwOrderdetail);
	
	void update(DsfwOrderdetailEntity dsfwOrderdetail);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
