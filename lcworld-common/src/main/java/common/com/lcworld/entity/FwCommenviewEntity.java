package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * VIEW
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-05 17:28:34
 */
public class FwCommenviewEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private Integer uid;
	//
	private String userName;
	//
	private String negative;
	//
	private String commonly;
	//
	private String good;
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
	 * 设置：
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：
	 */
	public void setNegative(String negative) {
		this.negative = negative;
	}
	/**
	 * 获取：
	 */
	public String getNegative() {
		return negative;
	}
	/**
	 * 设置：
	 */
	public void setCommonly(String commonly) {
		this.commonly = commonly;
	}
	/**
	 * 获取：
	 */
	public String getCommonly() {
		return commonly;
	}
	/**
	 * 设置：
	 */
	public void setGood(String good) {
		this.good = good;
	}
	/**
	 * 获取：
	 */
	public String getGood() {
		return good;
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
