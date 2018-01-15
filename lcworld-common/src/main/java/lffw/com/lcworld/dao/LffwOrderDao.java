package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.lcworld.dto.LffwOrderDTO;
import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.utils.Query;

/**
 * 理发服务订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-23 16:57:59
 */
public interface LffwOrderDao extends BaseDao<LffwOrderEntity> {

    List<LffwOrderDTO> queryOrderlist(Query q);

    int queryorderTotal(Query query);

    List<Map<String,Object>> queryorderList1(Query query);

    void autofinishOrder();

	
}
