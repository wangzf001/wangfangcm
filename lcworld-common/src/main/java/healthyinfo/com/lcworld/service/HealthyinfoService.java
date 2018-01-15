package com.lcworld.service;

import com.lcworld.entity.HealthyinfoEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 健康资讯
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 15:14:38
 */
public interface HealthyinfoService {
	
	HealthyinfoEntity queryObject(Integer id);
	
	List<HealthyinfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HealthyinfoEntity healthyinfo);
	
	void update(HealthyinfoEntity healthyinfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<com.lcworld.dto.HealthyinfoDTO> queryInfoList(Query q);
}
