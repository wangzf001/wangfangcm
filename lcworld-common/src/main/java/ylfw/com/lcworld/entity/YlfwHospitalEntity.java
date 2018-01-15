package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 医疗服务医院表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-20 13:09:47
 */
public class YlfwHospitalEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//医院名称
	private String name;

	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：医院名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：医院名称
	 */
	public String getName() {
		return name;
	}
}
