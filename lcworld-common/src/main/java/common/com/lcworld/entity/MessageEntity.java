package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-20 14:54:20
 */
public class MessageEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//消息标题
	private String messagetitle;
	//消息内容
	private String content;
	//详情ID
	private Integer detailId;
	//创建时间
	private Date createtime;
	//用户ID
	private Integer uid;
	//是否已读 0 未读 1 已读
	private Boolean isRead = Boolean.FALSE;
	//是否删除 0 未删除 1 删除
	private Boolean isDel = Boolean.FALSE;
	//消息类型 1、系统消息 2、评论消息 3、缴费通知 4、审核状态 5、活动通知 7、意见投诉
	private Integer messagetype;
	//新消息数量
	private Integer newCount;
	//消息状态
	private Integer read;
	public Integer getNewCount() {
		return newCount;
	}
	public void setNewCount(Integer newCount) {
		this.newCount = newCount;
	}
	public Integer getRead() {
		return read;
	}
	public void setRead(Integer read) {
		this.read = read;
	}
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
	 * 设置：消息标题
	 */
	public void setMessagetitle(String messagetitle) {
		this.messagetitle = messagetitle;
	}
	/**
	 * 获取：消息标题
	 */
	public String getMessagetitle() {
		return messagetitle;
	}
	/**
	 * 设置：消息内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：消息内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：详情ID
	 */
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
	/**
	 * 获取：详情ID
	 */
	public Integer getDetailId() {
		return detailId;
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
	 * 设置：用户ID
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户ID
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：是否已读 0 未读 1 已读
	 */
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	/**
	 * 获取：是否已读 0 未读 1 已读
	 */
	public Boolean getIsRead() {
		return isRead;
	}
	/**
	 * 设置：是否删除 0 未删除 1 删除
	 */
	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}
	/**
	 * 获取：是否删除 0 未删除 1 删除
	 */
	public Boolean getIsDel() {
		return isDel;
	}
	/**
	 * 设置：消息类型 1、系统消息 2、评论消息 3、缴费通知 4、审核状态 5、活动通知 7、意见投诉
	 */
	public void setMessagetype(Integer messagetype) {
		this.messagetype = messagetype;
	}
	/**
	 * 获取：消息类型 1、系统消息 2、评论消息 3、缴费通知 4、审核状态 5、活动通知 7、意见投诉
	 */
	public Integer getMessagetype() {
		return messagetype;
	}
}
