package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户银行卡
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 16:29:38
 */
public class UserBankCardEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//持卡人姓名
	private String username;
	//银行类型b
	private String banktype;
	//银行卡号
	private String cardnum;
	//预留电话
	private String mobile;
	//用户编号
	private Integer uid;
	//创建时间
	private Date createtime;
	//是否删除 1: 删除，0： 否
	private Integer isdelete;

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
	 * 设置：持卡人姓名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：持卡人姓名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：银行类型b
	 */
	public void setBanktype(String banktype) {
		this.banktype = banktype;
	}
	/**
	 * 获取：银行类型b
	 */
	public String getBanktype() {
		return banktype;
	}
	/**
	 * 设置：银行卡号
	 */
	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}
	/**
	 * 获取：银行卡号
	 */
	public String getCardnum() {
		return cardnum;
	}
	/**
	 * 设置：预留电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：预留电话
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：用户编号
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户编号
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：是否删除 1: 删除，0： 否
	 */
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	/**
	 * 获取：是否删除 1: 删除，0： 否
	 */
	public Integer getIsdelete() {
		return isdelete;
	}
}
