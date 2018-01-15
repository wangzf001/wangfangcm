package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-11 14:58:34
 */
public class CheckfailureReasonEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//服务类型
	private String servicecode;
	//
	private String reason;

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
	 * 设置：服务类型
	 */
	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}
	/**
	 * 获取：服务类型
	 */
	public String getServicecode() {
		return servicecode;
	}
	/**
	 * 设置：
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 获取：
	 */
	public String getReason() {
		return reason;
	}
}
