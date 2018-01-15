package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户钱包充值订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:38
 */
public class UserWalleorderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private Integer uid;
	// 金额
	private BigDecimal money;
	// 创建时间
	private Date createtime;
	// 支付类型 1: 支付宝，2：微信
	private Integer paytype;
	// 订单状态 1:下单，2：已支付，3：失败
	private Integer status;
	// 订单号
	private String ordercode;
	// 订单类型
	private Integer ordertype;
	//使用类型
	private Integer usetype;
	public Integer getUsetype() {
		return usetype;
	}

	public void setUsetype(Integer usetype) {
		this.usetype = usetype;
	}

	public Integer getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
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
	 * 设置：订单状态 1:下单，2：已支付，3：失败
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 获取：订单状态 1:下单，2：已支付，3：失败
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
