package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.LffwBarberWorksEntity;

import java.util.List;
import java.util.Map;

/**
 * 理发服务-理发师作品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
public interface LffwBarberWorksService extends ICollectionService<LffwBarberWorksEntity>{
	
	LffwBarberWorksEntity queryObject(Integer id);
	
	List<LffwBarberWorksEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwBarberWorksEntity lffwBarberWorks);
	
	void update(LffwBarberWorksEntity lffwBarberWorks);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<LffwBarberWorksEntity> queryindexworkList(JSONObject params);
}
