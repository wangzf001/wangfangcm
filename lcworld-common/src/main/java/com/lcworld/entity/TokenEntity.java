package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户Token
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:07
 */
public class TokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// 用户ID
	private Long userId;
	// token
	private String token;
	// 过期时间
	private Date expireTime;
	// 更新时间
	private Date updateTime;
	// 服务端:2,客户端:1
	private Integer signid;
	// 服务类型 id
	private Integer stid;

	/**
	 * 设置：用户ID
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 获取：用户ID
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

	public Integer getSignid() {
		return signid;
	}

	public void setSignid(Integer signid) {
		this.signid = signid;
	}

	public Integer getStid() {
		return stid;
	}

	public void setStid(Integer stid) {
		this.stid = stid;
	}

}
