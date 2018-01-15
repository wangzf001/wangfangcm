package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.entity.TsjyfwUserrecommandEntity;
import com.lcworld.utils.Query;

/**
 * 图书借阅服务-读者推荐
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
public interface TsjyfwUserrecommandDao extends BaseDao<TsjyfwUserrecommandEntity> {

    List<Map<String,Object>> queryrecommandList(Query query);

    int queryrecommandTotal(Query query);
	
}
