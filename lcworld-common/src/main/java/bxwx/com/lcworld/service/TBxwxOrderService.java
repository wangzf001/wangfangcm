package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.BxwxOrderDTO;
import com.lcworld.entity.BxwxAppointmentEntity;
import com.lcworld.entity.TBxwxOrderEntity;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;

/**
 * 报修维修订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 11:45:49
 */
public interface TBxwxOrderService extends IOrderService {
	
	TBxwxOrderEntity queryObject(Integer id);
	
	List<TBxwxOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TBxwxOrderEntity tBxwxOrder);
	
	void update(TBxwxOrderEntity tBxwxOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    List<BxwxOrderDTO> queryOrderlist(Query q);

    BxwxOrderDTO queryOrderDetail(JSONObject params);

    void saveorder(TBxwxOrderEntity order);

    int queryordermapTotal(Query query);

    List<Map<String ,Object>> queryordermapList(Query query);
    
    R generateOrder(TBxwxOrderEntity order,List<BxwxAppointmentEntity> appointmentList);

	R orderCancel(JSONObject params);
}
