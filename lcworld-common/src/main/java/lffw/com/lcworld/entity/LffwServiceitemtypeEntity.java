package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 理发服务-服务项目分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:13
 */
public class LffwServiceitemtypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String name;
	//
	private BigDecimal minprice;
	//
	private BigDecimal maxprice;

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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setMinprice(BigDecimal minprice) {
		this.minprice = minprice;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getMinprice() {
		return minprice;
	}
	/**
	 * 设置：
	 */
	public void setMaxprice(BigDecimal maxprice) {
		this.maxprice = maxprice;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getMaxprice() {
		return maxprice;
	}
}
