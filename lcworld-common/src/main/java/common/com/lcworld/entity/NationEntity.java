package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-11 10:35:19
 */
public class NationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//民族
	private Integer id;
	//
	private String name;

	/**
	 * 设置：民族
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：民族
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
