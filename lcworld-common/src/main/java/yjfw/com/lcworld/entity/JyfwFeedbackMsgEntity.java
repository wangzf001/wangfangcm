package com.lcworld.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 16:01:18
 */
public class JyfwFeedbackMsgEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//投诉id
	private Integer complaintId;
	//1用户2客服
	private Integer utype;
	//用户id
	private Integer uid;
	//服务用户id
	private Integer serviceuid;
	//发送内容
	private String content;
	//创建时间
	private Date createtime;
	//阅读状态0未读1已读
	private Integer readstatus;
	private String photo;
	private List<String> imgs = new ArrayList<>();
	
	
	public Integer getServiceuid() {
		return serviceuid;
	}
	public void setServiceuid(Integer serviceuid) {
		this.serviceuid = serviceuid;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public List<String> getImgs() {
		return imgs;
	}
	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
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
	 * 设置：投诉id
	 */
	public void setComplaintId(Integer complaintId) {
		this.complaintId = complaintId;
	}
	/**
	 * 获取：投诉id
	 */
	public Integer getComplaintId() {
		return complaintId;
	}
	/**
	 * 设置：1用户2客服
	 */
	public void setUtype(Integer utype) {
		this.utype = utype;
	}
	/**
	 * 获取：1用户2客服
	 */
	public Integer getUtype() {
		return utype;
	}
	/**
	 * 设置：用户id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：发送内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：发送内容
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
	 * 设置：阅读状态0未读1已读
	 */
	public void setReadstatus(Integer readstatus) {
		this.readstatus = readstatus;
	}
	/**
	 * 获取：阅读状态0未读1已读
	 */
	public Integer getReadstatus() {
		return readstatus;
	}
}
