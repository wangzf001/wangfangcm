package com.lcworld.dao;

import java.util.List;

import com.lcworld.entity.TVisitUserEntity;
import com.lcworld.utils.Query;

/**
 * 来访人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 14:23:43
 */
public interface TVisitUserDao extends BaseDao<TVisitUserEntity> {

    List<TVisitUserEntity> queryOrderList(Query q);
    
    TVisitUserEntity queryObject(Integer id);
	
}
