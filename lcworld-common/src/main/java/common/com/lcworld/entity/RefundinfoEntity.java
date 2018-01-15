package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 公用服务-支付信息
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-25 16:53:27
 */
public class RefundinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//用户id
	private Integer uid;
	//订单id
	private Integer orderid;
	//退款类型 (1:支付宝，2：微信，3：个人余额对私 ，4：集体余额对公，5：代金券对私，6：代金券对公)
	private Integer refundtype;
	//订单类型（1:网络报修，2：医疗服务，3：图书借阅，4：餐费充值，5：团队活动，6：预约理发，7：干洗店，8：办公用品，9：订餐，10：营养套餐，11：信息公布，12：意见反馈 ，13：健康资讯，14：专家坐诊  15：工作餐 16：干洗 17 : 医疗服务预约挂号挂号单号 18订水服务）
	private Integer ordertype;
	//退款状态 (0:退款中，1;成功，2：失败）
	private Integer status;
	//退款金额
	private BigDecimal refundmoney;
	//订单号
	private String ordercode;
	//退款单号
	private String refundordercode;
	//创建时间
	private Date createtime;
	//代金券数量
	private Integer vouchercount;
	//每张代金券金额
	private BigDecimal voucherprice;
	//退款总金额
	private BigDecimal totalrefundmoney;
	//三方支付交易单号
	private String transactioncode;
	
	
	public String getTransactioncode() {
		return transactioncode;
	}
	public void setTransactioncode(String transactioncode) {
		this.transactioncode = transactioncode;
	}
	public BigDecimal getTotalrefundmoney() {
		return totalrefundmoney;
	}
	public void setTotalrefundmoney(BigDecimal totalrefundmoney) {
		this.totalrefundmoney = totalrefundmoney;
	}
	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：用户id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：订单id
	 */
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	/**
	 * 获取：订单id
	 */
	public Integer getOrderid() {
		return orderid;
	}
	/**
	 * 设置：退款类型 (1:支付宝，2：微信，3：个人余额对私 ，4：集体余额对公，5：代金券对私，6：代金券对公)
            
	 */
	public void setRefundtype(Integer refundtype) {
		this.refundtype = refundtype;
	}
	/**
	 * 获取：退款类型 (1:支付宝，2：微信，3：个人余额对私 ，4：集体余额对公，5：代金券对私，6：代金券对公)
            
	 */
	public Integer getRefundtype() {
		return refundtype;
	}
	/**
	 * 设置：订单类型（1:网络报修，2：医疗服务，3：图书借阅，4：餐费充值，5：团队活动，6：预约理发，7：干洗店，8：办公用品，9：订餐，10：营养套餐，11：信息公布，12：意见反馈 ，13：健康资讯，14：专家坐诊  15：工作餐 16：干洗 17 : 医疗服务预约挂号挂号单号 18订水服务）
	 */
	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}
	/**
	 * 获取：订单类型（1:网络报修，2：医疗服务，3：图书借阅，4：餐费充值，5：团队活动，6：预约理发，7：干洗店，8：办公用品，9：订餐，10：营养套餐，11：信息公布，12：意见反馈 ，13：健康资讯，14：专家坐诊  15：工作餐 16：干洗 17 : 医疗服务预约挂号挂号单号 18订水服务）
	 */
	public Integer getOrdertype() {
		return ordertype;
	}
	/**
	 * 设置：退款状态 (0:退款中，1;成功，2：失败）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：退款状态 (0:退款中，1;成功，2：失败）
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：退款金额
	 */
	public void setRefundmoney(BigDecimal refundmoney) {
		this.refundmoney = refundmoney;
	}
	/**
	 * 获取：退款金额
	 */
	public BigDecimal getRefundmoney() {
		return refundmoney;
	}
	/**
	 * 设置：订单号
	 */
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	/**
	 * 获取：订单号
	 */
	public String getOrdercode() {
		return ordercode;
	}
	/**
	 * 设置：退款单号
	 */
	public void setRefundordercode(String refundordercode) {
		this.refundordercode = refundordercode;
	}
	/**
	 * 获取：退款单号
	 */
	public String getRefundordercode() {
		return refundordercode;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：代金券数量
	 */
	public void setVouchercount(Integer vouchercount) {
		this.vouchercount = vouchercount;
	}
	/**
	 * 获取：代金券数量
	 */
	public Integer getVouchercount() {
		return vouchercount;
	}
	/**
	 * 设置：每张代金券金额
	 */
	public void setVoucherprice(BigDecimal voucherprice) {
		this.voucherprice = voucherprice;
	}
	/**
	 * 获取：每张代金券金额
	 */
	public BigDecimal getVoucherprice() {
		return voucherprice;
	}
}
