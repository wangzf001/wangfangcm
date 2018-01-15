package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-13 19:08:42
 */
public class SysRoleTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//系统角色类型
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
	 * 设置：系统角色类型
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：系统角色类型
	 */
	public String getName() {
		return name;
	}
}
