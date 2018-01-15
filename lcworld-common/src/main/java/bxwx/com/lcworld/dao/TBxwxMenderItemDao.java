package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.BxwxMenderItemDTO;
import com.lcworld.entity.TBxwxMenderItemEntity;
import com.lcworld.utils.Query;

/**
 * 维修人维修项
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
public interface TBxwxMenderItemDao extends BaseDao<TBxwxMenderItemEntity> {
    void deleteByParams(JSONObject params);

    void savebench(List<TBxwxMenderItemEntity> rlist);

    List<BxwxMenderItemDTO> queryUserItemList(Query q);

    List<Map<String, Object>> querymenderitemlist(Query query);

    int querymenderitemTotal(Query query);
	
}
