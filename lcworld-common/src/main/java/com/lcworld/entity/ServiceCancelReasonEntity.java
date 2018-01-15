package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 取消原因表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-18 14:19:15
 */
public class ServiceCancelReasonEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//原因
	private String reason;
	//服务类
	private Integer type;

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
	 * 设置：原因
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 获取：原因
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * 设置：服务类
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：服务类
	 */
	public Integer getType() {
		return type;
	}
}
