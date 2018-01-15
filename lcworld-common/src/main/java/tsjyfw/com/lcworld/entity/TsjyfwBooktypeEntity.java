package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 图书借阅服务--图书分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
public class TsjyfwBooktypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//类型名称
	private String typename;

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
	 * 设置：类型名称
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}
	/**
	 * 获取：类型名称
	 */
	public String getTypename() {
		return typename;
	}
}
