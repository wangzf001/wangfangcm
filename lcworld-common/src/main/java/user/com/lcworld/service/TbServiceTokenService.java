package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TbServiceTokenEntity;
import com.lcworld.entity.TokenEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户Token
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-13 21:57:18
 */
public interface TbServiceTokenService {
	
	TbServiceTokenEntity queryObject(Long userId);
	
	List<TbServiceTokenEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TbServiceTokenEntity tbServiceToken);
	
	void update(TbServiceTokenEntity tbServiceToken);
	
	void delete(Long userId);
	
	void deleteBatch(Long[] userIds);
	/**
	 * 生成token
	 * @param userId  用户ID
	 * @return        返回token相关信息
	 */
	Map<String, Object> createToken(long userId, int servicetypeid);
	TbServiceTokenEntity queryByCondition(JSONObject params);
    void delByUid(Integer uid,Integer servicetypeid);

    TbServiceTokenEntity queryByToken(String token);
	
	
}
