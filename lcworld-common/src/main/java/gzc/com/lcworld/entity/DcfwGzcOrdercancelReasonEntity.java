package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 13:35:12
 */
public class DcfwGzcOrdercancelReasonEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//取消原因
	private String content;
	//原因使用数量
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
	 * 设置：取消原因
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：取消原因
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：原因使用数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：原因使用数量
	 */
	public Integer getCount() {
		return count;
	}
}
