package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TbMessageOrderMenderEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-26 14:24:46
 */
public interface TbMessageOrderMenderDao extends BaseDao<TbMessageOrderMenderEntity> {

	List<TbMessageOrderMenderEntity> queryListByInfo(JSONObject p);
	
	int unReadMessageCount(JSONObject p);
}
