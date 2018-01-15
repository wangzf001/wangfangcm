package com.lcworld.service;

import com.lcworld.entity.DeliverymanEntity;
import com.lcworld.entity.DsfwWaterEntity;


import java.util.List;
import java.util.Map;

/**
 * 理发服务-理发师
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-08 19:47:46
 */
public interface DsfwWaterService extends IOperationUserService<DsfwWaterEntity>{
	
	DsfwWaterEntity queryObject(Integer id);
	
	List<DsfwWaterEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DsfwWaterEntity dsfwWater);
	
	void update(DsfwWaterEntity dsfwWater);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	List<DsfwWaterEntity> queryMenderlist(Map<String,Object> query);
}
