package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.DcfwGzcOrderEntity;
import com.lcworld.utils.R;

/**
 * 订餐服务-工作餐订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 13:35:13
 */
public interface DcfwGzcOrderService extends IOrderService ,ExcelService<DcfwGzcOrderEntity>{
	
	DcfwGzcOrderEntity queryObject(Integer id);
	
	List<DcfwGzcOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(DcfwGzcOrderEntity dcfwGzcOrder);
	
	void update(DcfwGzcOrderEntity dcfwGzcOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	R generateOrder(JSONObject params);

	int invalidOrderBatch(Integer[] array);

	void updateBatch(Integer[] ids, int typeOrderStatusFinished);
}
