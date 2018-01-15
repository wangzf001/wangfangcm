package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 维修人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-14 11:50:03
 */
public class TBxwxMenderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	//真实姓名
	private String realname;
	//部门编号
	private Integer departid;
	//状态 1: 有效，0： 无效
	private Integer valid;
	//电话
	private String mobile;
	//登录编号
	private Integer loginid;
	//分数
	private Double score;
	//接单量
	private Integer count;
	//头像
	private String photo;
	//用户名
	private String username;
	//密码
	private String password;
	//密码
	private Date createtime;
	
	private String shanchanglingyu;
	
	public String getShanchanglingyu() {
		return shanchanglingyu;
	}

	public void setShanchanglingyu(String shanchanglingyu) {
		this.shanchanglingyu = shanchanglingyu;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
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
	 * 设置：部门编号
	 */
	public void setDepartid(Integer departid) {
		this.departid = departid;
	}
	/**
	 * 获取：部门编号
	 */
	public Integer getDepartid() {
		return departid;
	}
	/**
	 * 设置：状态 1: 有效，0： 无效
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	/**
	 * 获取：状态 1: 有效，0： 无效
	 */
	public Integer getValid() {
		return valid;
	}
	/**
	 * 设置：电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：电话
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：登录编号
	 */
	public void setLoginid(Integer loginid) {
		this.loginid = loginid;
	}
	/**
	 * 获取：登录编号
	 */
	public Integer getLoginid() {
		return loginid;
	}
	/**
	 * 设置：分数
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	/**
	 * 获取：分数
	 */
	public Double getScore() {
		return score;
	}
	/**
	 * 设置：接单量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：接单量
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：头像
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * 获取：头像
	 */
	public String getPhoto() {
		return photo;
	}
}
