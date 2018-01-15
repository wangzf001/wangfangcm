package com.lcworld.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 建议服务
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 16:01:19
 */
public class JyfwComplaintEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//投诉内容
	private String complaintContent;
	//用户id
	private Integer uid;
	//标签id
	private Integer tagid;
	//建议内容
	private String suggestContent;
	//1匿名0不匿名
	private Integer anonymous;
	//
	private Date createtime;
	//标签名称
	private String tagName;
	//标签名称
	private String imgsStr;
	private String username;
	private String photo;
	//标签名称
	private List<String> imgs = new ArrayList<>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getImgsStr() {
		return imgsStr;
	}
	public void setImgsStr(String imgsStr) {
		this.imgsStr = imgsStr;
	}
	public List<String> getImgs() {
		return imgs;
	}
	public void setImgs(List<String> imgs) {
		this.imgs = imgs;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
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
	 * 设置：投诉内容
	 */
	public void setComplaintContent(String complaintContent) {
		this.complaintContent = complaintContent;
	}
	/**
	 * 获取：投诉内容
	 */
	public String getComplaintContent() {
		return complaintContent;
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
	 * 设置：标签id
	 */
	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}
	/**
	 * 获取：标签id
	 */
	public Integer getTagid() {
		return tagid;
	}
	/**
	 * 设置：建议内容
	 */
	public void setSuggestContent(String suggestContent) {
		this.suggestContent = suggestContent;
	}
	/**
	 * 获取：建议内容
	 */
	public String getSuggestContent() {
		return suggestContent;
	}
	/**
	 * 设置：1匿名0不匿名
	 */
	public void setAnonymous(Integer anonymous) {
		this.anonymous = anonymous;
	}
	/**
	 * 获取：1匿名0不匿名
	 */
	public Integer getAnonymous() {
		return anonymous;
	}
	/**
	 * 设置：
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：
	 */
	public Date getCreatetime() {
		return createtime;
	}
}
