package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.BgypSkuDto;
import com.lcworld.entity.BgypfwSkuCataInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-27 16:25:55
 */
public interface BgypfwSkuCataInfoService {
	
	BgypfwSkuCataInfoEntity queryObject(Integer id);
	
	List<BgypfwSkuCataInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BgypfwSkuCataInfoEntity bgypfwSkuCataInfo);
	
	void update(BgypfwSkuCataInfoEntity bgypfwSkuCataInfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	List<BgypSkuDto> querySkuList(JSONObject params);

	void saveBench(List<BgypfwSkuCataInfoEntity> catainfolist);
	
	void deletebyskuid(Integer skuid);
}
