package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.ServiceWallelogEntity;
import com.lcworld.vo.service.owner.OwnerWalletHome;
import com.lcworld.vo.service.owner.ServiceOwnerWalletLog;

/**
 * 服务端钱包明细表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-18 16:06:23
 */
public interface ServiceWallelogDao extends BaseDao<ServiceWallelogEntity> {

	Double shouru(JSONObject p);

	List<ServiceOwnerWalletLog> serviceOwnerWalletLog(JSONObject p);

	
}
