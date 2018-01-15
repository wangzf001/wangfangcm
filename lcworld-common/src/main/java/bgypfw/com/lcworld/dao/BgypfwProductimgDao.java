package com.lcworld.dao;

import com.lcworld.entity.BgypfwProductimgEntity;

/**
 * 办公用品服务-商品规格图片
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
public interface BgypfwProductimgDao extends BaseDao<BgypfwProductimgEntity> {

	void deleteByPids(Integer[] ids);
	
}
