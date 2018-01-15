package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TFavorEntity;
import com.lcworld.utils.R;

import java.util.List;
import java.util.Map;

/**
 * 收藏
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 10:53:19
 */
public interface TFavorService {
	
	TFavorEntity queryObject(Integer id);
	
	List<TFavorEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TFavorEntity tFavor);
	
	void update(TFavorEntity tFavor);
	
	void delete(Integer id);

	R saveOrUpdate(TFavorEntity favor);

    /**若没有登录，直接返回未收藏，否则返回收藏状态
     * 
     * @param uid 
     * @param favortype 收藏类型 见  {@link com.lcworld.consts.FavorConst }
     * @param entityid  收藏对象id 
     *
     * @return 1:已收藏，0：未收藏
     */
    int  queryFavorStatus(Integer uid,Integer favortype, Integer entityid);

	R deleteBatch(JSONObject params);

}
