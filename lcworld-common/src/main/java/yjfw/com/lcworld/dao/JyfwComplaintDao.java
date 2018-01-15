package com.lcworld.dao;

import java.util.Map;

import com.lcworld.entity.JyfwComplaintEntity;

/**
 * 建议服务
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 16:01:19
 */
public interface JyfwComplaintDao extends BaseDao<JyfwComplaintEntity> {

	void addImgList(Map<String, Object> paramMap);
	
}
