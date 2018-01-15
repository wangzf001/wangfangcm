package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-26 14:24:46
 */
public class TbMessageOrderMenderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long autoId;
	//
	private String messageTitle;
	//
	private String messageContent;
	//
	private Date createTime;
	//
	private Integer uid;
	//1:报修维修；7:会议室；8:办公用品；18:订水；
	private Integer serverTypeId;
	//
	private Integer orderId;
	//
	private Integer isRead;
	//
	private Integer isDelete;

	/**
	 * 设置：
	 */
	public void setAutoId(Long autoId) {
		this.autoId = autoId;
	}
	/**
	 * 获取：
	 */
	public Long getAutoId() {
		return autoId;
	}
	/**
	 * 设置：
	 */
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	/**
	 * 获取：
	 */
	public String getMessageTitle() {
		return messageTitle;
	}
	/**
	 * 设置：
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	/**
	 * 获取：
	 */
	public String getMessageContent() {
		return messageContent;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
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
	 * 设置：1:报修维修；7:会议室；8:办公用品；18:订水；
	 */
	public void setServerTypeId(Integer serverTypeId) {
		this.serverTypeId = serverTypeId;
	}
	/**
	 * 获取：1:报修维修；7:会议室；8:办公用品；18:订水；
	 */
	public Integer getServerTypeId() {
		return serverTypeId;
	}
	/**
	 * 设置：
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：
	 */
	public Integer getOrderId() {
		return orderId;
	}
	/**
	 * 设置：
	 */
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	/**
	 * 获取：
	 */
	public Integer getIsRead() {
		return isRead;
	}
	/**
	 * 设置：
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取：
	 */
	public Integer getIsDelete() {
		return isDelete;
	}
}
