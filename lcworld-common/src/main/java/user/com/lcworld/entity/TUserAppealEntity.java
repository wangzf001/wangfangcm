package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 账号申诉
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 14:23:42
 */
public class TUserAppealEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//账号
	private String userName;
	//密码
	private String password;
	//身份证号
	private String idcard;
	//手机号
	private String mobile;
	//真实姓名
	private String realname;
	//原由
	private String reason;
	//申诉状态0:申诉，1：成功，2：失败
	private Integer status;
	//失败原因
	private String failurereason;

	/**
	 * 设置：编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：账号
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：账号
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：身份证号
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	/**
	 * 获取：身份证号
	 */
	public String getIdcard() {
		return idcard;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：真实姓名
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * 获取：真实姓名
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * 设置：原由
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 获取：原由
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * 设置：申诉状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：申诉状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：失败原因
	 */
	public void setFailurereason(String failurereason) {
		this.failurereason = failurereason;
	}
	/**
	 * 获取：失败原因
	 */
	public String getFailurereason() {
		return failurereason;
	}
}
