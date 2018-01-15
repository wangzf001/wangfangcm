package com.lcworld.dto;

import java.util.Date;

public class ServiceUserDTO {
	//id主键
	private Integer id;
	//密码
	private String password;
	//用户名
	private String username;
	//用户所属类型
	private Integer servicetypeid;
	//是否有效
	private Integer valid;
	//是否有效
	private Date createtime;
	//用户头像
	private String photo;
	//真实姓名
	private String realname;
	//手机号
	private String mobile;
	
	
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
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
	public Integer getServicetypeid() {
		return servicetypeid;
	}
	public void setServicetypeid(Integer servicetypeid) {
		this.servicetypeid = servicetypeid;
	}
	
}
