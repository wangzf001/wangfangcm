package com.lcworld.dao;

import java.util.Map;

import com.lcworld.entity.DsfwMenderEntity;

/**
 * 订水服务人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-15 13:51:56
 */
public interface DsfwMenderDao extends BaseDao<DsfwMenderEntity> {

	DsfwMenderEntity queryByCondition(Map<String, Object> params);
	
}
