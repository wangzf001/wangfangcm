package com.lcworld.dao;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.YlfwyyghOrderDTO;
import com.lcworld.dto.YlfwzjzzOrderDTO;
import com.lcworld.entity.YlfwZjzzOrderEntity;
import com.lcworld.utils.Query;

/**
 * 医疗服务专家坐诊订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
public interface YlfwZjzzOrderDao extends BaseDao<YlfwZjzzOrderEntity> {

    List<YlfwzjzzOrderDTO> queryOrderlist(Query q);

    YlfwzjzzOrderDTO queryOrderdetail(JSONObject params);

    List<Map<String,Object>> queryorderList(Query query);

    int queryorderTotal(Query query);
	
}
