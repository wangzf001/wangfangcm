package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 理发服务-订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-23 15:13:54
 */
public class LffwOrderdetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer itemid;
	//
	private Integer orderid;
	private Integer itemtypeid;
	private BigDecimal price;
	
	
	

	public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Integer getItemtypeid() {
        return itemtypeid;
    }
    public void setItemtypeid(Integer itemtypeid) {
        this.itemtypeid = itemtypeid;
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
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	/**
	 * 获取：
	 */
	public Integer getItemid() {
		return itemid;
	}
	/**
	 * 设置：
	 */
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	/**
	 * 获取：
	 */
	public Integer getOrderid() {
		return orderid;
	}
}
