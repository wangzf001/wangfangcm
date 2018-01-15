package com.lcworld.entity;

public class TalkVo {
	private String serviceUsername;
	private String servicePhoto;
	private String newContent;
	private Integer targetId;
	private Integer uid;
	private Integer serviceUid;
	private Integer targetType;
	
	public Integer getTargetType() {
		return targetType;
	}
	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}
	public String getServiceUsername() {
		return serviceUsername;
	}
	public void setServiceUsername(String serviceUsername) {
		this.serviceUsername = serviceUsername;
	}
	public String getServicePhoto() {
		return servicePhoto;
	}
	public void setServicePhoto(String servicePhoto) {
		this.servicePhoto = servicePhoto;
	}
	public String getNewContent() {
		return newContent;
	}
	public void setNewContent(String newContent) {
		this.newContent = newContent;
	}
	public Integer getTargetId() {
		return targetId;
	}
	public void setTargetId(Integer targetId) {
		this.targetId = targetId;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getServiceUid() {
		return serviceUid;
	}
	public void setServiceUid(Integer serviceUid) {
		this.serviceUid = serviceUid;
	}
	
}
