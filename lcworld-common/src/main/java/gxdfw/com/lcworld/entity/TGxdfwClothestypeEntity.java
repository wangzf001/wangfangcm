package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 干洗店服务-衣服类型
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:55
 */
public class TGxdfwClothestypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//名称
	private String name;
	//价格
	private BigDecimal price;

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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
}
