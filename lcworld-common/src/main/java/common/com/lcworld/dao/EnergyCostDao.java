package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.EnergyCostEntity;


/**
 * 能耗表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-13 15:50:14
 */
public interface EnergyCostDao extends BaseDao<EnergyCostEntity> {
	
	List<EnergyCostEntity>  energyCostQuery(Map<String, Object> map);
	EnergyCostEntity  energyCostTotalQuery(Map<String, Object> map);
	
	Integer bothCostQuery(Integer energyType);
}
