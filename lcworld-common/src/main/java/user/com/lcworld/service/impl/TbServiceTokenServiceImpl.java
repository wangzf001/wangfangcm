package com.lcworld.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dao.TbServiceTokenDao;
import com.lcworld.entity.TbServiceTokenEntity;
import com.lcworld.entity.TokenEntity;
import com.lcworld.service.TbServiceTokenService;



@Service("tbServiceTokenService")
public class TbServiceTokenServiceImpl implements TbServiceTokenService {
	@Autowired
	private TbServiceTokenDao tbServiceTokenDao;
	
	@Override
	public TbServiceTokenEntity queryObject(Long userId){
		return tbServiceTokenDao.queryObject(userId);
	}
	
	@Override
	public List<TbServiceTokenEntity> queryList(Map<String, Object> map){
		return tbServiceTokenDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tbServiceTokenDao.queryTotal(map);
	}
	
	@Override
	public void save(TbServiceTokenEntity tbServiceToken){
		tbServiceTokenDao.save(tbServiceToken);
	}
	
	@Override
	public void update(TbServiceTokenEntity tbServiceToken){
		tbServiceTokenDao.update(tbServiceToken);
	}
	
	@Override
	public void delete(Long userId){
		tbServiceTokenDao.delete(userId);
	}
	
	@Override
	public void deleteBatch(Long[] userIds){
		tbServiceTokenDao.deleteBatch(userIds);
	}
	@Override
	public TbServiceTokenEntity queryByCondition(JSONObject params){
		return tbServiceTokenDao.queryByCondition(params);
	}

	@Override
	public Map<String, Object> createToken(long userId, int servicetypeid) {
		//生成一个token
		String token = UUID.randomUUID().toString();
		//当前时间
		Date now = new Date();

		//过期时间
		//Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		JSONObject params = new JSONObject();
		params.put("userid", userId);
		params.put("servicetypeid", servicetypeid);
		TbServiceTokenEntity tokenEntity = queryByCondition(params);
		if(tokenEntity == null){
			tokenEntity = new TbServiceTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setServicetypeid(servicetypeid);
			//tokenEntity.setExpireTime(expireTime);

			//保存token
			save(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			//tokenEntity.setExpireTime(expireTime);

			//更新token
			update(tokenEntity);
		}

		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		//map.put("expire", EXPIRE);

		return map;
	}

	@Override
	public void delByUid(Integer uid,Integer servicetypeid) {
		tbServiceTokenDao.delByUid(uid,servicetypeid);
	}

	@Override
	public TbServiceTokenEntity queryByToken(String token) {
		return tbServiceTokenDao.queryByToken(token);
	}
	
}
