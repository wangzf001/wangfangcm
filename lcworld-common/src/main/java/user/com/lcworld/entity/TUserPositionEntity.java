package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 职位表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 14:23:42
 */
public class TUserPositionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//名称
	private String name;

	/**
	 * 设置：编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：编号
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
