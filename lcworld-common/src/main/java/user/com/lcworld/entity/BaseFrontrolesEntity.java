package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 角色表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-11 15:56:43
 */
public class BaseFrontrolesEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//角色名称
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
	 * 设置：角色名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：角色名称
	 */
	public String getName() {
		return name;
	}
}
