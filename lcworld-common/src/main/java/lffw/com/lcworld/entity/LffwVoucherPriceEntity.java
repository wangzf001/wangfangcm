package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-01 15:45:30
 */
public class LffwVoucherPriceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//理发券单价
	private BigDecimal voucherprice;

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
	 * 设置：理发券单价
	 */
	public void setVoucherprice(BigDecimal voucherprice) {
		this.voucherprice = voucherprice;
	}
	/**
	 * 获取：理发券单价
	 */
	public BigDecimal getVoucherprice() {
		return voucherprice;
	}
}
