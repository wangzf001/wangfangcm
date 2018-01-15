package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.PurchaseDTO;
import com.lcworld.entity.OfficePurchaseCountEntity;
import com.lcworld.utils.Query;

/**
 * 处室对公账户
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-13 10:33:57
 */
public interface OfficePurchaseCountDao extends BaseDao<OfficePurchaseCountEntity> {

    List<OfficePurchaseCountEntity> queryOPCList(Query query);

    int queryOPCTotal(Query query);

    List<OfficePurchaseCountEntity> queryFOPCList(JSONObject obj);

    List<PurchaseDTO> getPubPurchaseList(JSONObject param);
	
}
