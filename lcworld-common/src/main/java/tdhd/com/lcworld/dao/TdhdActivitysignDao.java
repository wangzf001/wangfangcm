package com.lcworld.dao;

import java.util.List;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.TdhdActivityEnrollDTO;
import com.lcworld.dto.TdhdActivityUserDTO;
import com.lcworld.entity.TdhdActivitysignEntity;
import com.lcworld.utils.Query;

/**
 * 团队活动系统-报名表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 13:45:51
 */
public interface TdhdActivitysignDao extends BaseDao<TdhdActivitysignEntity> {

    List<TdhdActivityUserDTO> queryuserList(Map<String,Object> query);

    int queryuserTotal(Query query);

    List<Map<String, Object>> queryuserList1(JSONObject params);

    List<TdhdActivityEnrollDTO> queryEnrollList(Map<String,Object> query);
	
}
