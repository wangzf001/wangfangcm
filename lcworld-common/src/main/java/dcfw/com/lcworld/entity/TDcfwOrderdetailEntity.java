package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 订餐服务订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
public class TDcfwOrderdetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "TDcfwOrderdetailEntity [id=" + id + ", orderid=" + orderid + ", foodid=" + foodid + ", count=" + count
				+ ", price=" + price + ", createtime=" + createtime + ", totalprice=" + totalprice + ", foodName="
				+ foodName + ", foodImg=" + foodImg + ", foodOriginal=" + foodOriginal + "]";
	}
	//
	private Integer id;
	//
	private Integer orderid;
	//食物id
	private Integer foodid;
	//数量
	private Integer count;
	//单价
	private BigDecimal price;
	//创建时间
	private Date createtime;
	//总价
	private BigDecimal totalprice;
	//套餐名称
	private String foodName;
	//套餐图片
	private String foodImg;
	//套餐用料
	private String foodOriginal;
	//套餐剩余
	private Integer foodRemain;

	public Integer getFoodRemain() {
		return foodRemain;
	}
	public void setFoodRemain(Integer foodRemain) {
		this.foodRemain = foodRemain;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public String getFoodImg() {
		return foodImg;
	}
	public void setFoodImg(String foodImg) {
		this.foodImg = foodImg;
	}
	public String getFoodOriginal() {
		return foodOriginal;
	}
	public void setFoodOriginal(String foodOriginal) {
		this.foodOriginal = foodOriginal;
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
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	/**
	 * 获取：
	 */
	public Integer getOrderid() {
		return orderid;
	}
	/**
	 * 设置：食物id
	 */
	public void setFoodid(Integer foodid) {
		this.foodid = foodid;
	}
	/**
	 * 获取：食物id
	 */
	public Integer getFoodid() {
		return foodid;
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
}
