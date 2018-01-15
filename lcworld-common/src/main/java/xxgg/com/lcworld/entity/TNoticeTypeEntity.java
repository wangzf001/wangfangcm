package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 公告分类
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
public class TNoticeTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//分类名称
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
	 * 设置：分类名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：分类名称
	 */
	public String getName() {
		return name;
	}
}
