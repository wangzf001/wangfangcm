package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.LffwBarberWorksEntity;

/**
 * 理发服务-理发师作品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
public interface LffwBarberWorksDao extends BaseDao<LffwBarberWorksEntity> {

    List<LffwBarberWorksEntity> queryindexworkList(JSONObject params);
	
}
