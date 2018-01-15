package com.lcworld.dao;

import java.util.Map;

import com.lcworld.entity.TbServiceTokenEntity;
import com.lcworld.entity.TokenEntity;

/**
 * 用户Token
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-13 21:57:18
 */
public interface TbServiceTokenDao extends BaseDao<TbServiceTokenEntity> {

	TbServiceTokenEntity queryByCondition(Map<String,Object> params);

	void delByUid(Integer uid, Integer servicetypeid);

	TbServiceTokenEntity queryByToken(String token);
	
}
