package com.lcworld.service;

import com.lcworld.dto.LffwOrderDTO;
import com.lcworld.entity.LffwOrderEntity;
import com.lcworld.test.queue.interf.RemindOrderService;
import com.lcworld.utils.Query;
import com.lcworld.vo.LffwOrderVO;
import com.lcworld.vo.PayOrderVo;

import java.util.List;
import java.util.Map;

/**
 * 理发服务订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
public interface LffwOrderService extends IOrderService{
	
	LffwOrderEntity queryObject(Integer id);
	
	List<LffwOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LffwOrderEntity lffwOrder);
	
	void update(LffwOrderEntity lffwOrder);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

    LffwOrderEntity saveorder(LffwOrderVO ordervo) throws Exception;

    List<LffwOrderDTO> queryOrderlist(Query q);

    List<Map<String,Object>> queryorderList(Query query);

    int queryorderTotal(Query query);

    void savemixpay(PayOrderVo order);

    void autofinishOrder();
    
}
