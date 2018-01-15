package com.lcworld.dao;

import com.lcworld.entity.LffwVoucherEntity;

/**
 * 理发服务--代金券
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:13
 */
public interface LffwVoucherDao extends BaseDao<LffwVoucherEntity> {

	LffwVoucherEntity queryByUid(Integer uid);
	
}
