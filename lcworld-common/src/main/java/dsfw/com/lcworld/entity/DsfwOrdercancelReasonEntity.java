package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 订水服务-取消订单原因
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:20
 */
public class DsfwOrdercancelReasonEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//评价内容
	private String content;
	//订单编号
	private Integer count;

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
	 * 设置：评价内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：评价内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：订单编号
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：订单编号
	 */
	public Integer getCount() {
		return count;
	}
}
