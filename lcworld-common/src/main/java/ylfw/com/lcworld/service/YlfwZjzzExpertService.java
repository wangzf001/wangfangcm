package com.lcworld.service;

import com.lcworld.dto.DoctorDTO;
import com.lcworld.dto.ZjzzExpertDTO;
import com.lcworld.entity.YlfwZjzzExpertEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 医疗服务专家坐诊医生表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
public interface YlfwZjzzExpertService extends ICollectionService<YlfwZjzzExpertEntity>{
	
	YlfwZjzzExpertEntity queryObject(Integer id);
	
	List<YlfwZjzzExpertEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwZjzzExpertEntity ylfwZjzzExpert);
	
	void update(YlfwZjzzExpertEntity ylfwZjzzExpert);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<ZjzzExpertDTO> queryDoctorList(Query q);

    List<Map<String, Object>> queryexpertList(Query query);

    int queryexpertTotal(Query query);
}
