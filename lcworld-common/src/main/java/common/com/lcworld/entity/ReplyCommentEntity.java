package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 回复评论表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-20 13:29:16
 */
public class ReplyCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//回复时间
	private Date createtime;
	//订单服务类型
	private Integer ordertype;
	//内容
	private String content;
	//回复者
	private Integer uid;
	//评论id
	private Integer commentid;

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
	 * 设置：回复时间
	 */
	public void setcreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：回复时间
	 */
	public Date getcreatetime() {
		return createtime;
	}
	/**
	 * 设置：订单服务类型
	 */
	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}
	/**
	 * 获取：订单服务类型
	 */
	public Integer getOrdertype() {
		return ordertype;
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
	 * 设置：回复者
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：回复者
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：评论id
	 */
	public void setCommentid(Integer commentid) {
		this.commentid = commentid;
	}
	/**
	 * 获取：评论id
	 */
	public Integer getCommentid() {
		return commentid;
	}
}
