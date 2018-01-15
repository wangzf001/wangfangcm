package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * VIEW
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-11 10:49:38
 */
public class OrderViewEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private Integer oid;
	//
	private String uid;
	//
	private Integer paystatus;
	//
	private String username;
	//
	private String remark;
	//
	private String address;
	//
	private Date createtime;
	//
	private String ordercode;
	//
	private String type;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	/**
	 * 获取：
	 */
	public Integer getOid() {
		return oid;
	}
	/**
	 * 设置：
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * 获取：
	 */
	public String getUid() {
		return uid;
	}
	/**
	 * 设置：
	 */
	public void setPaystatus(Integer paystatus) {
		this.paystatus = paystatus;
	}
	/**
	 * 获取：
	 */
	public Integer getPaystatus() {
		return paystatus;
	}
	/**
	 * 设置：
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：
	 */
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	/**
	 * 获取：
	 */
	public String getOrdercode() {
		return ordercode;
	}
	/**
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
}
