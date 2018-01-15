package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-10 13:54:03
 */
public class UserDepositEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//押金金额
	private BigDecimal deposit;
	//用户id
	private Integer uid;
	//押金类型1订水押金
	private Integer type;

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
	 * 设置：押金金额
	 */
	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
	/**
	 * 获取：押金金额
	 */
	public BigDecimal getDeposit() {
		return deposit;
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
	 * 设置：押金类型1订水押金
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：押金类型1订水押金
	 */
	public Integer getType() {
		return type;
	}
}
