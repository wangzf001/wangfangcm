package com.lcworld.vo;

import java.math.BigDecimal;

public class RefundVo {
	//订单类型（1:网络报修，2：医疗服务，3：图书借阅，4：餐费充值，5：团队活动，6：预约理发，7：干洗店，8：办公用品，9：订餐，10：营养套餐，11：信息公布，12：意见反馈 ，13：健康资讯，14：专家坐诊  15：工作餐 16：干洗 17 : 医疗服务预约挂号挂号单号 18订水服务）
    private Integer ordertype;
    //退款类型 (1:支付宝，2：微信，3：个人余额对私 ，4：集体余额对公，5：代金券对私，6：代金券对公)
    private Integer refundtype;
    //订单号
    private String ordercode;
    private Integer orderid;
    //订单状态 
    private Integer status;
    //总金额
    private BigDecimal totalmoney;
    //退款金额
    private BigDecimal refundmoney;
    //退单号
    private String refundOrdercode;
    
	public String getRefundOrdercode() {
		return refundOrdercode;
	}
	public void setRefundOrdercode(String refundOrdercode) {
		this.refundOrdercode = refundOrdercode;
	}
	public Integer getRefundtype() {
		return refundtype;
	}
	public void setRefundtype(Integer refundtype) {
		this.refundtype = refundtype;
	}
	public Integer getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}
	public String getOrdercode() {
		return ordercode;
	}
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public BigDecimal getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(BigDecimal totalmoney) {
		this.totalmoney = totalmoney;
	}
	public BigDecimal getRefundmoney() {
		return refundmoney;
	}
	public void setRefundmoney(BigDecimal refundmoney) {
		this.refundmoney = refundmoney;
	}
    
}
