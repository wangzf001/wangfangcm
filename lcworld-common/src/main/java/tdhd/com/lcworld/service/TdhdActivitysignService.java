package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.TdhdActivityDTO;
import com.lcworld.dto.TdhdActivityEnrollDTO;
import com.lcworld.dto.TdhdActivityUserDTO;
import com.lcworld.entity.TdhdActivityEntity;
import com.lcworld.entity.TdhdActivitysignEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 团队活动系统-报名表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 13:45:51
 */
public interface TdhdActivitysignService {
	
	TdhdActivitysignEntity queryObject(Integer asId);
	
	List<TdhdActivitysignEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TdhdActivitysignEntity tdhdActivitysign);
	
	void update(TdhdActivitysignEntity tdhdActivitysign);
	
	void delete(Integer asId);
	
	void deleteBatch(Integer[] asIds);

    TdhdActivitysignEntity queryExistMember(TdhdActivitysignEntity sign);

    void saveSign(TdhdActivitysignEntity sign, TdhdActivityEntity activity);

    List<TdhdActivityUserDTO> queryuserList(Map<String,Object> query);

    int queryuserTotal(Query query);

    List<Map<String,Object>> queryuserList1(JSONObject params);

	List<TdhdActivityEnrollDTO> queryEnrollList(Map<String,Object> query);
}
