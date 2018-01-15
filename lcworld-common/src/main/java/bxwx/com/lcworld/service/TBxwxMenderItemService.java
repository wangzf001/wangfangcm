package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.BxwxMenderItemDTO;
import com.lcworld.entity.TBxwxMenderItemEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 维修人维修项
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
public interface TBxwxMenderItemService {
	
	TBxwxMenderItemEntity queryObject(Integer id);
	
	List<TBxwxMenderItemEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TBxwxMenderItemEntity tBxwxMenderItem);
	
	void update(TBxwxMenderItemEntity tBxwxMenderItem);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void deleteByParams(JSONObject params);

    void savebench(List<TBxwxMenderItemEntity> rlist);

    List<BxwxMenderItemDTO> queryUserItemList(Query q);

    List<Map<String, Object>> querymenderitemlist(Query query);

    int querymenderitemTotal(Query query);
}
