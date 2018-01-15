package com.lcworld.dao;

import java.util.HashMap;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.LffwBarberDTO;
import com.lcworld.entity.LffwBarberEntity;

/**
 * 理发服务-理发师
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:15
 */
public interface LffwBarberDao extends BaseDao<LffwBarberEntity> {

    List<LffwBarberDTO> querybarberList(JSONObject params);
	
}
