package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户Token
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-13 21:57:18
 */
public class TbServiceTokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long userId;
	//token
	private String token;
	//过期时间
	private Date expireTime;
	//更新时间
	private Date updateTime;
	//服务类型id
	private Integer servicetypeid;

	/**
	 * 设置：
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 获取：token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * 设置：过期时间
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	/**
	 * 获取：过期时间
	 */
	public Date getExpireTime() {
		return expireTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	public Integer getServicetypeid() {
		return servicetypeid;
	}
	public void setServicetypeid(Integer servicetypeid) {
		this.servicetypeid = servicetypeid;
	}
	
}
