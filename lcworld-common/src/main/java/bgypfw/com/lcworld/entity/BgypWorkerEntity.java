package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-08 16:15:29
 */
public class BgypWorkerEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String photo;
	//
	private String name;
	//职称
	private Integer positionid;
	//
	private Double score;
	//
	private String mobile;
	//
	private String brief;
	//
	private Integer valid;
	//默认与手机号一致
	private String username;
	//
	private String password;

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
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * 获取：
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：职称
	 */
	public void setPositionid(Integer positionid) {
		this.positionid = positionid;
	}
	/**
	 * 获取：职称
	 */
	public Integer getPositionid() {
		return positionid;
	}
	/**
	 * 设置：
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	/**
	 * 获取：
	 */
	public Double getScore() {
		return score;
	}
	/**
	 * 设置：
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}
	/**
	 * 获取：
	 */
	public String getBrief() {
		return brief;
	}
	/**
	 * 设置：
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	/**
	 * 获取：
	 */
	public Integer getValid() {
		return valid;
	}
	/**
	 * 设置：默认与手机号一致
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：默认与手机号一致
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：
	 */
	public String getPassword() {
		return password;
	}
}
