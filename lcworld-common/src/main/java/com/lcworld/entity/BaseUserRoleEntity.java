package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户角色表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-31 11:23:51
 */
public class BaseUserRoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//角色id
	private Integer roleid;
	//商户id
	private Integer uid;

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
	 * 设置：角色id
	 */
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	/**
	 * 获取：角色id
	 */
	public Integer getRoleid() {
		return roleid;
	}
	/**
	 * 设置：商户id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：商户id
	 */
	public Integer getUid() {
		return uid;
	}
}
