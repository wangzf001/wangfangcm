package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.LffwBarberDTO;
import com.lcworld.entity.LffwBarberEntity;

/**
 * 理发服务-理发师
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:15
 */
public interface LffwBarberService extends ICollectionService<LffwBarberEntity>{
	
	LffwBarberEntity queryObject(Integer id);
	
	List<LffwBarberEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwBarberEntity lffwBarber);
	
	void update(LffwBarberEntity lffwBarber);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<LffwBarberDTO> querybarberList(JSONObject params);
}
