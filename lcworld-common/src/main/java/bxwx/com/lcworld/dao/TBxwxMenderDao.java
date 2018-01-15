package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.dto.ServiceUserDTO;
import com.lcworld.entity.TBxwxMenderEntity;

/**
 * 维修人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-14 11:50:03
 */
public interface TBxwxMenderDao extends BaseDao<TBxwxMenderEntity> {

    List<TBxwxMenderEntity> queryMenderlist(Map<String,Object> query);
    TBxwxMenderEntity queryByCondition(Map<String, Object> params);
	int countByMobileAndServiceTypeAndId(ServiceUserDTO user);
}
