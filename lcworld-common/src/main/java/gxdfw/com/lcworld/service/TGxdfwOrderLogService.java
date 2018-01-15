package com.lcworld.service;

import com.lcworld.entity.TGxdfwOrderLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 干洗店服务-订单记录
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:52
 */
public interface TGxdfwOrderLogService {
	
	TGxdfwOrderLogEntity queryObject(Integer id);
	
	List<TGxdfwOrderLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TGxdfwOrderLogEntity tGxdfwOrderLog);
	
	void update(TGxdfwOrderLogEntity tGxdfwOrderLog);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	TGxdfwOrderLogEntity queryByOid(Integer oid);
	/**
	 * 通过oid来更新订单日志
	 * @param oid
	 */
	void updateByOid(TGxdfwOrderLogEntity orderLog);
}
