package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.PurchaseDTO;
import com.lcworld.entity.DeparpurchaseCountEntity;
import com.lcworld.utils.Query;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-13 10:33:57
 */
public interface DeparpurchaseCountDao extends BaseDao<DeparpurchaseCountEntity> {

    List<DeparpurchaseCountEntity> queryDPCList(Query query);

    int queryDPCTotal(Query query);

    List<DeparpurchaseCountEntity> queryFDPCList(JSONObject obj);

    List<PurchaseDTO> getPubPurchaseList(JSONObject param);
	
}
