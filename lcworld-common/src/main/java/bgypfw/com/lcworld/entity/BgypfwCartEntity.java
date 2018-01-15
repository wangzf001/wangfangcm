package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 办公用品服务-购物车
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public class BgypfwCartEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer uid;
	//规格id
	private Integer skuid;
	//商品id
	private Integer productid;
	//创建时间
	private Date createtime;
	//数量
	private Integer count;
	//库存
	private BgypfwSkuidEntity sku = new BgypfwSkuidEntity();
	
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
	 * 设置：
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：
	 */
	public Integer getUid() {
		return uid;
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
}
