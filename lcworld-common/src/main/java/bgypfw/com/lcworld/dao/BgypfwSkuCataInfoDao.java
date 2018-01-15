package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.BgypSkuDto;
import com.lcworld.entity.BgypfwSkuCataInfoEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-27 16:25:55
 */
public interface BgypfwSkuCataInfoDao extends BaseDao<BgypfwSkuCataInfoEntity> {

	List<BgypSkuDto> querySkuList(JSONObject params);

	void deletebyskuid(Integer skuid);
	
}
