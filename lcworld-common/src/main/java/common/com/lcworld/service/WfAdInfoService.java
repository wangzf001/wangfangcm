package com.lcworld.service;

import com.lcworld.entity.WfAdInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 广告管理
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2018-01-17 20:32:57
 */
public interface WfAdInfoService {
	
	WfAdInfoEntity queryObject(Integer id);
	
	List<WfAdInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WfAdInfoEntity wfAdInfo);
	
	void update(WfAdInfoEntity wfAdInfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
