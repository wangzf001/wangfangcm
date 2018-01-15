package com.lcworld.service;

import com.lcworld.entity.DsfwSendtimeEntity;

import java.util.List;
import java.util.Map;

/**
 * 订水服务-送水时间
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:19
 */
public interface DsfwSendtimeService {
	
	DsfwSendtimeEntity queryObject(Integer id);
	
	List<DsfwSendtimeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DsfwSendtimeEntity dsfwSendtime);
	
	void update(DsfwSendtimeEntity dsfwSendtime);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
