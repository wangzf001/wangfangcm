package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 团队活动系统-报名表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 13:45:51
 */
public class TdhdActivitysignEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer asId;
	//
	private Integer aId;
	//
	private Integer userId;
	//
	private String asAvatar;
	//
	private String asUsername;
	//
	private String asMebile;

	/**
	 * 设置：
	 */
	public void setAsId(Integer asId) {
		this.asId = asId;
	}
	/**
	 * 获取：
	 */
	public Integer getAsId() {
		return asId;
	}
	/**
	 * 设置：
	 */
	public void setAId(Integer aId) {
		this.aId = aId;
	}
	/**
	 * 获取：
	 */
	public Integer getAId() {
		return aId;
	}
	/**
	 * 设置：
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：
	 */
	public void setAsAvatar(String asAvatar) {
		this.asAvatar = asAvatar;
	}
	/**
	 * 获取：
	 */
	public String getAsAvatar() {
		return asAvatar;
	}
	/**
	 * 设置：
	 */
	public void setAsUsername(String asUsername) {
		this.asUsername = asUsername;
	}
	/**
	 * 获取：
	 */
	public String getAsUsername() {
		return asUsername;
	}
	/**
	 * 设置：
	 */
	public void setAsMebile(String asMebile) {
		this.asMebile = asMebile;
	}
	/**
	 * 获取：
	 */
	public String getAsMebile() {
		return asMebile;
	}
}
