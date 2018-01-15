package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 理发服务职称
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 14:01:24
 */
public class LffwPositionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
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
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
}
