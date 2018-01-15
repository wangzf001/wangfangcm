package com.lcworld.dao;

import java.util.Map;

import com.lcworld.entity.BgypfwCategoryEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
public interface BgypfwCategoryDao extends BaseDao<BgypfwCategoryEntity> {
    BgypfwCategoryEntity queryCategoryByName(Map<String,Object> params);
}
