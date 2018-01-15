package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.LffwscheduleDTO;
import com.lcworld.entity.LffwPeriodtypeEntity;

import java.util.List;
import java.util.Map;

/**
 * 理发服务--时间段类型
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 19:30:54
 */
public interface LffwPeriodtypeService {
	
	LffwPeriodtypeEntity queryObject(Integer id);
	
	List<LffwPeriodtypeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwPeriodtypeEntity lffwPeriodtype);
	
	void update(LffwPeriodtypeEntity lffwPeriodtype);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<LffwscheduleDTO> querytimelist(JSONObject params);
}
