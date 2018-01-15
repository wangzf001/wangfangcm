package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-11 15:13:39
 */
public class DeliveryOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//配送员id
	private Integer did;
	//订单id
	private Integer orderid;
	//创建时间
	private Date createtime;
	//配送时间
	private Date deliverytime;
	//配送状态1配送中2配送完成
	private Integer status;
	//送单类型
	private Integer ordertype;
	
	public Integer getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
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
	 * 设置：配送员id
	 */
	public void setDid(Integer did) {
		this.did = did;
	}
	/**
	 * 获取：配送员id
	 */
	public Integer getDid() {
		return did;
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
	 * 设置：配送时间
	 */
	public void setDeliverytime(Date deliverytime) {
		this.deliverytime = deliverytime;
	}
	/**
	 * 获取：配送时间
	 */
	public Date getDeliverytime() {
		return deliverytime;
	}
	/**
	 * 设置：配送状态1配送中2配送完成
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：配送状态1配送中2配送完成
	 */
	public Integer getStatus() {
		return status;
	}
}
