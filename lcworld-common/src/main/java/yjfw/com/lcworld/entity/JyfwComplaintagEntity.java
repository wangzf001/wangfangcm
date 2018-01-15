package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 16:22:08
 */
public class JyfwComplaintagEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//标签名
	private String tagName;
	//创建时间
	private Date createtime;
	//投诉数
	private Integer count;

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
	 * 设置：标签名
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	/**
	 * 获取：标签名
	 */
	public String getTagName() {
		return tagName;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：投诉数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：投诉数
	 */
	public Integer getCount() {
		return count;
	}
}
