package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 报修维修项
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 18:19:23
 */
public class TBxwxItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//名称
	private String name;
	//费用上限
	private BigDecimal pricehighlimit;
	//费用下限
	private BigDecimal pricelowlimit;

	/**
	 * 设置：编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：编号
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
	 * 设置：费用上限
	 */
	public void setPricehighlimit(BigDecimal pricehighlimit) {
		this.pricehighlimit = pricehighlimit;
	}
	/**
	 * 获取：费用上限
	 */
	public BigDecimal getPricehighlimit() {
		return pricehighlimit;
	}
	/**
	 * 设置：费用下限
	 */
	public void setPricelowlimit(BigDecimal pricelowlimit) {
		this.pricelowlimit = pricelowlimit;
	}
	/**
	 * 获取：费用下限
	 */
	public BigDecimal getPricelowlimit() {
		return pricelowlimit;
	}
}
