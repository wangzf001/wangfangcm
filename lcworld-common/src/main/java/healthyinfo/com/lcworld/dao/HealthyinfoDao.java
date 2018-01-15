package com.lcworld.dao;

import java.util.List;

import com.lcworld.dto.HealthyinfoDTO;
import com.lcworld.entity.HealthyinfoEntity;
import com.lcworld.utils.Query;

/**
 * 健康资讯
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 15:14:38
 */
public interface HealthyinfoDao extends BaseDao<HealthyinfoEntity> {

    List<HealthyinfoDTO> queryInfoList(Query q);
	
}
