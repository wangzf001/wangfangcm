package com.lcworld.dao;

import java.util.List;

import com.lcworld.entity.TsjyfwBookCopyEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-25 17:48:09
 */
public interface TsjyfwBookCopyDao extends BaseDao<TsjyfwBookCopyEntity> {

    void saveAddOrderCountBench(List<Integer> ids);
	
}
