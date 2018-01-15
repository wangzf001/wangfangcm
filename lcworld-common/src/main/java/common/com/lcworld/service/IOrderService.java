package com.lcworld.service;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.lcworld.dto.BxwxOrderDTO;
import com.lcworld.entity.RefundinfoEntity;
import com.lcworld.utils.Query;
import com.lcworld.utils.R;
import com.lcworld.vo.CommentVo;
import com.lcworld.vo.PayOrderVo;

public interface IOrderService {

    PayOrderVo getOrderVo(PayOrderVo pay) throws Exception;

    /**notice : 订单中orderid 不唯一，orderid 和ordertype 或者ordercode 唯一，推荐使用ordercode 
     * @param order
     */
    void savePayed(PayOrderVo orderVo);
    /**
     * 取消订单
     * @param orderVo
     */
    R cancelOrder(Integer id,String cancelreason);
    /**
     * 删除订单
     * @param orderVo
     */
    void deleteOrder(Integer id);
    /**
     * 取消订单
     * @param files 
     * @param orderVo
     */
    R addComment(CommentVo comment, MultipartFile[] files);
    
    /**完成订单
     * @param id
     * @return
     */
    R finishOrder(Integer id);
    /**获取订单内送货时间
     * @param id
     * @return
     */
    Date getDeliverytime(Integer id);
    /**获取订单内送货时间
     * @param id
     * @return
     */
    R updateOrderStatus(Integer id,Integer lastStatus,Integer newStatus);
    /**
     * 获取退款信息
     * @param refundVo
     * @return
     */
	RefundinfoEntity getRefundInfo(RefundinfoEntity refundVo);
	/**
     * 保存退款状态
     * @param refundVo
     * @return
     */
	void saveRefund(RefundinfoEntity refundVo);
	/**
     * 订单列表
     * @param refundVo
     * @return
     */
	List<?> OrderList(Query q);
	/**
     * 订单详情
	 * @param <T>
     * @param refundVo
     * @return
     */
	<T> T OrderDetail(JSONObject params);
	/**
     * 删除订单
	 * @param <T>
     * @param refundVo
     * @return
     */
	void deleteOrders(JSONObject in);
	/**
     * 已完成订单
	 * @param <T>
     * @param refundVo
     * @return
     */
	void finishOrders(JSONObject p);
	/**
     * 立即服务订单
	 * @param <T>
     * @param refundVo
     * @return
     */
	void instantService(String id ,Integer uid);
	 /**
     * 取消订单
     * @param orderVo
     */
    R cancelOrders(JSONObject in);
    /**
     * 立即配送
     * @param orderVo
     */
    void distribution(JSONObject in);

    /**
     * 拒绝订单
     * @param p
     */
	void refuseOrder(JSONObject p);

	void deleteOverOrder(JSONObject in);
}
