package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
public class HysfwConferenceEquipmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//设备名
	private String name;
	//设备编号
	private String code;

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
	 * 设置：设备名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：设备名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：设备编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：设备编号
	 */
	public String getCode() {
		return code;
	}
}
