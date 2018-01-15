package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.BgypfwMenderEntity;
import com.lcworld.entity.TBxwxMenderEntity;

/**
 * 维修人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-12 17:02:33
 */
public interface BgypfwMenderDao extends BaseDao<BgypfwMenderEntity> {

	BgypfwMenderEntity queryByCondition(Map<String, Object> params);
	List<BgypfwMenderEntity> queryMenderlist(Map<String,Object> query);
	
}
