package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 消息设置
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-15 16:29:18
 */
public class TNoticeSetEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer uid;
	//消息类型  1： 新对话消息通知，2： 新资讯消息通知
	private Integer noticetype;
	//开启状态
	private Integer status;

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
	 * 设置：消息类型  1： 新对话消息通知，2： 新资讯消息通知
	 */
	public void setNoticetype(Integer noticetype) {
		this.noticetype = noticetype;
	}
	/**
	 * 获取：消息类型  1： 新对话消息通知，2： 新资讯消息通知
	 */
	public Integer getNoticetype() {
		return noticetype;
	}
	/**
	 * 设置：开启状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：开启状态
	 */
	public Integer getStatus() {
		return status;
	}
}
