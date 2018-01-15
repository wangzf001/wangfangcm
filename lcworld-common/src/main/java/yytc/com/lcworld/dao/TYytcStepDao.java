package com.lcworld.dao;

import com.lcworld.entity.TYytcStepEntity;

/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
public interface TYytcStepDao extends BaseDao<TYytcStepEntity> {
	/**
	 * 删除菜单下所有步骤
	 * @param mIds
	 */
	void deleteByMids(Integer[] mIds);
	
}
