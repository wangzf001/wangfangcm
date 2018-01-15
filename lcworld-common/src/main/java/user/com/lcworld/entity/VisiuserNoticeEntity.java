package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 来访人员消息
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-29 10:13:39
 */
public class VisiuserNoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//发送方类型 1: 用户，2 ： 维修人员
	private Integer utype;
	//发送方id
	private Integer postid;
	//接收方id
	private Integer getid;
	//内容
	private String content;
	//创建时间
	private Date createtime;
	//接收方类型 
	private Integer gettype;
	//发送方阅读状态 0 未读，1：已读
	private Integer postreadstatus;
	//接收方阅读状态  0 未读，1：已读
	private Integer getreadstatus;

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
	 * 设置：发送方类型 1: 用户，2 ： 维修人员
	 */
	public void setUtype(Integer utype) {
		this.utype = utype;
	}
	/**
	 * 获取：发送方类型 1: 用户，2 ： 维修人员
	 */
	public Integer getUtype() {
		return utype;
	}
	/**
	 * 设置：发送方id
	 */
	public void setPostid(Integer postid) {
		this.postid = postid;
	}
	/**
	 * 获取：发送方id
	 */
	public Integer getPostid() {
		return postid;
	}
	/**
	 * 设置：接收方id
	 */
	public void setGetid(Integer getid) {
		this.getid = getid;
	}
	/**
	 * 获取：接收方id
	 */
	public Integer getGetid() {
		return getid;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
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
	 * 设置：接收方类型 
	 */
	public void setGettype(Integer gettype) {
		this.gettype = gettype;
	}
	/**
	 * 获取：接收方类型 
	 */
	public Integer getGettype() {
		return gettype;
	}
	/**
	 * 设置：发送方阅读状态 0 未读，1：已读
	 */
	public void setPostreadstatus(Integer postreadstatus) {
		this.postreadstatus = postreadstatus;
	}
	/**
	 * 获取：发送方阅读状态 0 未读，1：已读
	 */
	public Integer getPostreadstatus() {
		return postreadstatus;
	}
	/**
	 * 设置：接收方阅读状态  0 未读，1：已读
	 */
	public void setGetreadstatus(Integer getreadstatus) {
		this.getreadstatus = getreadstatus;
	}
	/**
	 * 获取：接收方阅读状态  0 未读，1：已读
	 */
	public Integer getGetreadstatus() {
		return getreadstatus;
	}
}
