package com.lcworld.service;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.entity.TsjyfwOrderEntity;
import com.lcworld.utils.Query;
import com.lcworld.vo.TstjfwOrderVO;

import java.util.List;
import java.util.Map;

/**
 * 图书借阅服务--借阅图书
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 16:38:40
 */
public interface TsjyfwOrderService extends IOrderService{
	
	TsjyfwOrderEntity queryObject(Integer id);
	
	List<TsjyfwOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TsjyfwOrderEntity tsjyfwOrder);
	
	void update(TsjyfwOrderEntity tsjyfwOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    JSONObject saveorder(TstjfwOrderVO ordervo);

    List<com.lcworld.dto.TsjyfwOrderDTO> queryorderlist(Query q);

    List<Map<String,Object>> queryorderList(Query query);

    int queryorderTotal(Query query);
}
