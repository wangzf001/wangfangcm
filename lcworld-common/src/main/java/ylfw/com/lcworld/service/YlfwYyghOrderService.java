package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.YlfwyyghOrderDTO;
import com.lcworld.entity.YlfwYyghOrderEntity;
import com.lcworld.utils.Query;

import java.util.List;
import java.util.Map;

/**
 * 医疗服务预约挂号订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 10:55:47
 */
public interface YlfwYyghOrderService  extends IOrderService {
	
	YlfwYyghOrderEntity queryObject(Integer id);
	
	List<YlfwYyghOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwYyghOrderEntity ylfwYyghOrder);
	
	void update(YlfwYyghOrderEntity ylfwYyghOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<YlfwyyghOrderDTO> queryOrderlist(Query q);

    List<YlfwyyghOrderDTO> queryOrderdetail(JSONObject params);

    YlfwYyghOrderEntity saveorder(YlfwYyghOrderEntity order);
}
