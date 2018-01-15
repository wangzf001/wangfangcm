package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-30 15:30:18
 */
public class PurchaseTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//采购账号类型表
	private String name;
	//服务号
	private String servicecode;

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
	 * 设置：采购账号类型表
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：采购账号类型表
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：服务号
	 */
	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}
	/**
	 * 获取：服务号
	 */
	public String getServicecode() {
		return servicecode;
	}
}
