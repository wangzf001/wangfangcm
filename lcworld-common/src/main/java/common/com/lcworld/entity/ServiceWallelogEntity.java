package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 服务端钱包明细表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-18 16:06:23
 */
public class ServiceWallelogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//用户ID
	private Integer uid;
	//金额
	private String money;
	//描述
	private String content;
	//使用类型：1、收入 2、支出 3、提现
	private Integer useType;
	//服务类型ID
	private Integer serviceType;
	//创建时间
	private String creatTime;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户ID
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：金额
	 */
	public void setMoney(String money) {
		this.money = money;
	}
	/**
	 * 获取：金额
	 */
	public String getMoney() {
		return money;
	}
	/**
	 * 设置：描述
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：描述
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：使用类型：1、收入 2、支出 3、提现
	 */
	public void setUseType(Integer useType) {
		this.useType = useType;
	}
	/**
	 * 获取：使用类型：1、收入 2、支出 3、提现
	 */
	public Integer getUseType() {
		return useType;
	}
	/**
	 * 设置：服务类型ID
	 */
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
	/**
	 * 获取：服务类型ID
	 */
	public Integer getServiceType() {
		return serviceType;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	/**
	 * 获取：创建时间
	 */
	public String getCreatTime() {
		return creatTime;
	}
}
