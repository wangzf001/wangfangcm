package com.lcworld.dao;

import java.util.List;

import com.lcworld.dto.UserCarDTO;
import com.lcworld.entity.TCarUsercarinfoEntity;
import com.lcworld.utils.Query;

/**
 * 用户车辆信息表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-28 17:21:21
 */
public interface TCarUsercarinfoDao extends BaseDao<TCarUsercarinfoEntity> {

    List<UserCarDTO> queryUserCarinfoList(Query query);
	
}
