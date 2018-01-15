package com.lcworld.service;

import com.lcworld.entity.LffwVoucherPriceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-01 15:45:30
 */
public interface LffwVoucherPriceService {
	
	LffwVoucherPriceEntity queryObject(Integer id);
	
	List<LffwVoucherPriceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwVoucherPriceEntity lffwVoucherPrice);
	
	void update(LffwVoucherPriceEntity lffwVoucherPrice);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
