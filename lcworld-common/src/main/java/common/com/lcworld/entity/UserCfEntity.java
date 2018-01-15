package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-09 15:27:08
 */
public class UserCfEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//卡号
	private String cardNum;
	//实体卡号（预留）
	private String entityCardNumber;
	//
	private BigDecimal remainSum;
	//
	private Integer userId;
	private BigDecimal subsidySum;
	
	public BigDecimal getSubsidySum() {
		return subsidySum;
	}
	public void setSubsidySum(BigDecimal subsidySum) {
		this.subsidySum = subsidySum;
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
	 * 设置：卡号
	 */
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	/**
	 * 获取：卡号
	 */
	public String getCardNum() {
		return cardNum;
	}
	/**
	 * 设置：实体卡号（预留）
	 */
	public void setEntityCardNumber(String entityCardNumber) {
		this.entityCardNumber = entityCardNumber;
	}
	/**
	 * 获取：实体卡号（预留）
	 */
	public String getEntityCardNumber() {
		return entityCardNumber;
	}
	/**
	 * 设置：
	 */
	public void setRemainSum(BigDecimal remainSum) {
		this.remainSum = remainSum;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getRemainSum() {
		return remainSum;
	}
	/**
	 * 设置：
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Integer getUserId() {
		return userId;
	}
}
