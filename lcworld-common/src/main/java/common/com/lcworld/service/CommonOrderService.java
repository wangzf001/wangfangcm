package com.lcworld.service;

import java.util.List;
import java.util.Map;

import com.lcworld.dto.BxwxOrderDTO;
import com.lcworld.utils.R;

public interface CommonOrderService {
    /**
     * 订单列表
     * @param orderVo
     */
    List<?> OrderList(Map<String, Object> map);
    /**
     * 删除订单
     * @param orderVo
     */
    void deleteOrder (String ordercode);
    /**
     * 订单详情
     * @param orderVo
     */
    BxwxOrderDTO orderDetail(String ordercode);
    /**
     * 完成订单
     * @param orderVo
     * @return
     */
    void completeOrder(String biz);
    /**
     * 取消订单
     * @param orderVo
     * @return
     */
    void cancelorder(String ordercode);
    /**
     * 订单立即服务
     * @param orderVo
     * @return
     */
    R BeginService(String biz,String token);
}
