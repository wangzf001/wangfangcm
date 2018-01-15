package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 用户餐卡充值订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-23 11:08:21
 */
public class UserCfczOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//用户id
	private Integer uid;
	//金额
	private BigDecimal money;
	//创建时间
	private Date createtime;
	//支付类型 1: 支付宝，2：微信
	private Integer paytype;
	//订单状态 1:下单，2：已支付，3：充值失败4：充值成功
	private Integer status;
	//订单号
	private String ordercode;
	//卡号
	private String cardNum;
	
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
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
	 * 设置：金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：金额
	 */
	public BigDecimal getMoney() {
		return money;
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
	 * 设置：支付类型 1: 支付宝，2：微信
	 */
	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	/**
	 * 获取：支付类型 1: 支付宝，2：微信
	 */
	public Integer getPaytype() {
		return paytype;
	}
	/**
	 * 设置：订单状态 1:下单，2：已支付，3：充值失败4：充值成功
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态 1:下单，2：已支付，3：充值失败4：充值成功
	 */
	public Integer getStatus() {
		return status;
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
}
