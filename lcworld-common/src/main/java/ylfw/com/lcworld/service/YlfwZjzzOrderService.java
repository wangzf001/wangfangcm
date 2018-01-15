package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.YlfwyyghOrderDTO;
import com.lcworld.dto.YlfwzjzzOrderDTO;
import com.lcworld.entity.YlfwZjzzOrderEntity;
import com.lcworld.test.queue.interf.RemindOrderService;
import com.lcworld.test.queue.vo.RemindOrderVo;
import com.lcworld.utils.Query;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 医疗服务专家坐诊订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-04 13:26:42
 */
public interface YlfwZjzzOrderService extends IOrderService,RemindOrderService<YlfwZjzzOrderEntity>{
	
	YlfwZjzzOrderEntity queryObject(Integer id);
	
	List<YlfwZjzzOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(YlfwZjzzOrderEntity ylfwZjzzOrder);
	
	void update(YlfwZjzzOrderEntity ylfwZjzzOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    YlfwZjzzOrderEntity saveorder(YlfwZjzzOrderEntity order) throws ParseException;

    List<YlfwzjzzOrderDTO> queryOrderlist(Query q);

    YlfwzjzzOrderDTO queryOrderdetail(JSONObject params);

    List<Map<String,Object>> queryorderList(Query query);

    int queryorderTotal(Query query);

}
