package com.lcworld.service;

import com.lcworld.entity.LffwVoucherLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 代金券记录
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-31 14:40:06
 */
public interface LffwVoucherLogService {
	
	LffwVoucherLogEntity queryObject(Integer id);
	
	List<LffwVoucherLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwVoucherLogEntity lffwVoucherLog);
	
	void update(LffwVoucherLogEntity lffwVoucherLog);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
