package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 公用服务-支付信息
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-30 10:02:39
 */
public class PayinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer uid;
	//订单id
	private Integer orderid;
	//支付类型 (1:支付宝，2：微信，3：个人余额对私 ，4：集体余额对公，5：代金券对私，6：代金券对公)
            
	private Integer paytype;
	//订单类型（1:网络报修，2：医疗服务，3：图书借阅，4：餐费充值，5：团队活动，6：预约理发，7：干洗店，8：办公用品，9：订餐，10：营养套餐，11：信息公布，12：意见反馈 ，13：健康资讯，14：专家坐诊  15：工作餐 16：干洗 17 : 医疗服务预约挂号挂号单号 18订水服务）
	private Integer ordertype;
	//支付状态 (0:付款中，1;成功，2：失败）
	private Integer status;
	//支付金额
	private BigDecimal paymoney;
	//订单号
	private String ordercode;
	//支付订单号
	private String transactionId;
	//创建时间
	private Date createtime;
	//支付时间
	private Date paytime;
	//代金券数量
	private Integer vouchercount;
	//每张代金券金额
	private BigDecimal voucherprice;
	//支付备注
	private String payremark;
	private Integer mixpay;
	private Integer lockcount;
	
	
	
	public Integer getLockcount() {
        return lockcount;
    }
    public void setLockcount(Integer lockcount) {
        this.lockcount = lockcount;
    }
    public Integer getMixpay() {
        return mixpay;
    }
    public void setMixpay(Integer mixpay) {
        this.mixpay = mixpay;
    }
    public String getPayremark() {
		return payremark;
	}
	public void setPayremark(String payremark) {
		this.payremark = payremark;
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
	 * 设置：
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：
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
	 * 设置：支付类型 (1:支付宝，2：微信，3：个人余额对私 ，4：集体余额对公，5：代金券对私，6：代金券对公)
            
	 */
	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	/**
	 * 获取：支付类型 (1:支付宝，2：微信，3：个人余额对私 ，4：集体余额对公，5：代金券对私，6：代金券对公)
            
	 */
	public Integer getPaytype() {
		return paytype;
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
	 * 设置：支付状态 (0:付款中，1;成功，2：失败）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：支付状态 (0:付款中，1;成功，2：失败）
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：支付金额
	 */
	public void setPaymoney(BigDecimal paymoney) {
		this.paymoney = paymoney;
	}
	/**
	 * 获取：支付金额
	 */
	public BigDecimal getPaymoney() {
		return paymoney;
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
	 * 设置：支付订单号
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * 获取：支付订单号
	 */
	public String getTransactionId() {
		return transactionId;
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
	 * 设置：支付时间
	 */
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	/**
	 * 获取：支付时间
	 */
	public Date getPaytime() {
		return paytime;
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
