package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.BxwxOrderProcessDTO;
import com.lcworld.entity.TBxwxOrderProcessesEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单流程表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
public interface TBxwxOrderProcessesService {
	
	TBxwxOrderProcessesEntity queryObject(Integer id);
	
	List<TBxwxOrderProcessesEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TBxwxOrderProcessesEntity tBxwxOrderProcesses);
	
	void update(TBxwxOrderProcessesEntity tBxwxOrderProcesses);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<BxwxOrderProcessDTO> queryProcessList(JSONObject params);

    String getDatail(TBxwxOrderProcessesEntity tBxwxOrderProcesses);

    void saveProcesses(TBxwxOrderProcessesEntity tBxwxOrderProcesses);
}
