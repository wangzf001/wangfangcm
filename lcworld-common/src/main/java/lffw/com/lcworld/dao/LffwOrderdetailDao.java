package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.dto.LffwOrderDTO;
import com.lcworld.entity.LffwOrderdetailEntity;
import com.lcworld.utils.Query;

/**
 * 理发服务-订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-23 15:13:54
 */
public interface LffwOrderdetailDao extends BaseDao<LffwOrderdetailEntity> {

    List<Map<String, Object>> querydetailList(Map<String, Object> query);

    int querydetailTotal(Query query);
	
}
