package com.lcworld.dao;

import com.lcworld.entity.BuildingEntity;

/**
 * 楼栋表
 *
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-06 19:19:30
 */
public interface BuildingDao extends BaseDao<BuildingEntity> {
    BuildingEntity queryObjectByName(String buildname);
}
