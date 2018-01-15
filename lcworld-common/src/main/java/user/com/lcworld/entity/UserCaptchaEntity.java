package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户验证码
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-05 11:05:36
 */
public class UserCaptchaEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String username;
	//
	private String captcha;
	//截止日期
	private Date deadline;

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
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：
	 */
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	/**
	 * 获取：
	 */
	public String getCaptcha() {
		return captcha;
	}
	/**
	 * 设置：截止日期
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	/**
	 * 获取：截止日期
	 */
	public Date getDeadline() {
		return deadline;
	}
}
