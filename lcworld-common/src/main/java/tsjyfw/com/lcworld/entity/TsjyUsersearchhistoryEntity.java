package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 图书借阅用户搜索记录表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-23 16:57:00
 */
public class TsjyUsersearchhistoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String key;
	//
	private Integer uid;
	//
	private Date createtime;

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
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * 获取：
	 */
	public String getKey() {
		return key;
	}
	/**
	 * 设置：
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}
}
