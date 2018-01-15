package com.lcworld.service;

import com.lcworld.entity.YlfwYyghDiseaseEntity;

import java.util.List;
import java.util.Map;

/**
 * 医疗服务预约挂号疾病表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:52
 */
public interface YlfwYyghDiseaseService {
	
	YlfwYyghDiseaseEntity queryObject(Integer id);
	
	List<YlfwYyghDiseaseEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwYyghDiseaseEntity ylfwYyghDisease);
	
	void update(YlfwYyghDiseaseEntity ylfwYyghDisease);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
