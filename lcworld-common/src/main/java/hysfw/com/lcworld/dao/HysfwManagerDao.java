package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.HysfwManagerEntity;

/**
 * 理发服务-理发师
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-08 19:47:46
 */
public interface HysfwManagerDao extends BaseDao<HysfwManagerEntity> {

	HysfwManagerEntity queryByCondition(Map<String, Object> params);

	List<HysfwManagerEntity> queryMenderlist(Map<String, Object> query);
	
}
