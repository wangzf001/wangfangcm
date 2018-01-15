package com.lcworld.dao;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.YlfwyyghOrderDTO;
import com.lcworld.entity.YlfwYyghOrderEntity;
import com.lcworld.utils.Query;

/**
 * 医疗服务预约挂号订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 18:22:47
 */
public interface YlfwYyghOrderDao extends BaseDao<YlfwYyghOrderEntity> {

    List<YlfwyyghOrderDTO> queryOrderlist(Query q);

    List<YlfwyyghOrderDTO> queryOrderdetail(JSONObject params);
	
}
