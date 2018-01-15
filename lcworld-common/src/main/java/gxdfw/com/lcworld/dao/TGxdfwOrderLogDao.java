package com.lcworld.dao;

import com.lcworld.entity.TGxdfwOrderLogEntity;

/**
 * 干洗店服务-订单记录
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:52
 */
public interface TGxdfwOrderLogDao extends BaseDao<TGxdfwOrderLogEntity> {
	/**
	 * 通过订单id修改订单日志状态
	 * @param oid
	 */
	void updateByOid(Integer oid);
	
}
