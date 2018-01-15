package com.lcworld.service;
 
import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.TdhdActivityDTO;
import com.lcworld.entity.TdhdActivityEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 团队活动系统-活动
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 13:45:52
 */
public interface TdhdActivityService extends ICollectionService<TdhdActivityEntity>{
	
	TdhdActivityEntity queryObject(Integer aId);
	
	List<TdhdActivityEntity> queryList(Map<String, Object> map);

	List<TdhdActivityDTO> queryDTOList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TdhdActivityEntity tdhdActivity);
	
	void update(TdhdActivityEntity tdhdActivity);
	
	void delete(Integer aId);
	
	void deleteBatch(Integer[] aIds);

    List<com.lcworld.dto.TdhdActivityDTO> queryActivityList(JSONObject jsonObject);

}
