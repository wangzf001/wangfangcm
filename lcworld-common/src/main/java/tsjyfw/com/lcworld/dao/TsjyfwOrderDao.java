package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.dto.TsjyfwOrderDTO;
import com.lcworld.entity.TsjyfwOrderEntity;
import com.lcworld.utils.Query;

/**
 * 图书借阅服务--借阅图书
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 16:38:40
 */
public interface TsjyfwOrderDao extends BaseDao<TsjyfwOrderEntity> {

    List<TsjyfwOrderDTO> queryorderlist(Query q);

    List<Map<String,Object>> queryorderList(Query query);

    int queryorderTotal(Query query);
	
}
