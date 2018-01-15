package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 用户钱包
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:38
 */
public class UserWalletEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer uid;
	//可用金额
	private BigDecimal remain;
	//支付密码
	private String paypass;
	//对公余额
	private BigDecimal publicRemain;
	
	private String userName;
	
	public BigDecimal getPublicRemain() {
		return publicRemain;
	}
	public void setPublicRemain(BigDecimal publicRemain) {
		this.publicRemain = publicRemain;
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
	 * 设置：可用金额
	 */
	public void setRemain(BigDecimal remain) {
		this.remain = remain;
	}
	/**
	 * 获取：可用金额
	 */
	public BigDecimal getRemain() {
		return remain;
	}
	/**
	 * 设置：支付密码
	 */
	public void setPaypass(String paypass) {
		this.paypass = paypass;
	}
	/**
	 * 获取：支付密码
	 */
	public String getPaypass() {
		return paypass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
