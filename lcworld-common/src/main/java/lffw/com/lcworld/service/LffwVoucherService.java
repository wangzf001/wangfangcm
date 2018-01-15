package com.lcworld.service;

import com.lcworld.entity.LffwVoucherEntity;
import com.lcworld.entity.PayinfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 理发服务--代金券
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:13
 */
public interface LffwVoucherService {
	
	LffwVoucherEntity queryObject(Integer id);
	
	List<LffwVoucherEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwVoucherEntity lffwVoucher);
	
	void update(LffwVoucherEntity lffwVoucher);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    void saveVoucherPayed(LffwVoucherEntity voucher, PayinfoEntity pay);

	LffwVoucherEntity queryByUid(Integer integer);
}
