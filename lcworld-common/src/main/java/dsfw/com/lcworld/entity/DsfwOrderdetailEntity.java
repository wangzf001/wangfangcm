package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.mchange.v2.c3p0.stmt.GooGooStatementCache;



/**
 * 订水服务-订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:19
 */
public class DsfwOrderdetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer orderid;
	//商品id
	private Integer goodsid;
	//数量
	private Integer count;
	//单价
	private BigDecimal price;
	//创建时间
	private Date createtime;
	//总价
	private BigDecimal totalprice;
	private String goodsName;
	private String goodsImg;
	private DsfwGoodsEntity goods;
	
	
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public DsfwGoodsEntity getGoods() {
		return goods;
	}
	public void setGoods(DsfwGoodsEntity goods) {
		this.goods = goods;
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
	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}
	/**
	 * 获取：食物id
	 */
	public Integer getGoodsid() {
		return goodsid;
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
