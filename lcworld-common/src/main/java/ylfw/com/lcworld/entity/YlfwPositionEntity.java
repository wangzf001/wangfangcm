package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 医疗服务--职称
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-20 13:09:47
 */
public class YlfwPositionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//名称
	private String name;

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
	 * 设置：名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：名称
	 */
	public String getName() {
		return name;
	}
}
