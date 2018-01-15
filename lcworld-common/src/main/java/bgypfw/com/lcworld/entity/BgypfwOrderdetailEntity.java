package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 办公用品服务-订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public class BgypfwOrderdetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//规格id
	private Integer skuid;
	//商品id
	private Integer productid;
	//商品名
	private String productname;
	//数量
	private Integer count;
	//单价
	private BigDecimal price;
	//总价
	private BigDecimal totalprice;
	//创建时间
	private Date createtime;
	//订单id
	private Integer orderid;
	private Integer paystatus;
	private String suppliername;
	
	
	public String getSuppliername() {
		return suppliername;
	}
	public void setSuppliername(String suppliername) {
		this.suppliername = suppliername;
	}
	private BgypfwSkuidEntity sku = new BgypfwSkuidEntity();
	
	
	public Integer getPaystatus() {
		return paystatus;
	}
	public void setPaystatus(Integer paystatus) {
		this.paystatus = paystatus;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public BgypfwSkuidEntity getSku() {
		return sku;
	}
	public void setSku(BgypfwSkuidEntity sku) {
		this.sku = sku;
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
	 * 设置：规格id
	 */
	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}
	/**
	 * 获取：规格id
	 */
	public Integer getSkuid() {
		return skuid;
	}
	/**
	 * 设置：商品id
	 */
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	/**
	 * 获取：商品id
	 */
	public Integer getProductid() {
		return productid;
	}
	/**
	 * 设置：数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：数量
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：单价
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：总价
	 */
	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}
	/**
	 * 获取：总价
	 */
	public BigDecimal getTotalprice() {
		return totalprice;
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
	 * 设置：订单id
	 */
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	/**
	 * 获取：订单id
	 */
	public Integer getOrderid() {
		return orderid;
	}
}
