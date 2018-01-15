package com.lcworld.dao;

import com.lcworld.entity.BgypfwProductEntity;

/**
 * 办公用品服务-商品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
public interface BgypfwProductDao extends BaseDao<BgypfwProductEntity> {
    BgypfwProductEntity queryProductByName(String productname);
	
}
