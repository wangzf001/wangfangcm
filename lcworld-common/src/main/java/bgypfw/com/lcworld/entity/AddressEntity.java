package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 19:32:34
 */
public class AddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//用户id
	private Integer uid;
	//用户真名
	private String realname;
	//手机号
	private String mobile;
	//送货地址
	private String address;
	//是否为默认0否1是
	private Integer isDefault;

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
	 * 设置：用户id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：用户真名
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * 获取：用户真名
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：送货地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：送货地址
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * 设置：是否为默认0否1是
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * 获取：是否为默认0否1是
	 */
	public Integer getIsDefault() {
		return isDefault;
	}
}
