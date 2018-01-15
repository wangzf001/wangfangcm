package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.TdhdActivityDTO;
import com.lcworld.entity.TdhdActivityEntity;

/**
 * 团队活动系统-活动
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 13:45:52
 */
public interface TdhdActivityDao extends BaseDao<TdhdActivityEntity> {

    List<TdhdActivityDTO> queryActivityList(JSONObject q);

    List<TdhdActivityDTO> queryDTOList(Map<String, Object> map);

}
