package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户车辆信息表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-28 17:21:21
 */
public class TCarUsercarinfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//车辆类型编号
	private String cartypeid;
	//用户编号
	private Integer uid;
	//
	private String precarcode;
	//
	private String suffixcarcode;
	//认证id
	private Integer authid;

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
	 * 设置：车辆类型编号
	 */
	public void setCartypeid(String cartypeid) {
		this.cartypeid = cartypeid;
	}
	/**
	 * 获取：车辆类型编号
	 */
	public String getCartypeid() {
		return cartypeid;
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
	 * 设置：
	 */
	public void setPrecarcode(String precarcode) {
		this.precarcode = precarcode;
	}
	/**
	 * 获取：
	 */
	public String getPrecarcode() {
		return precarcode;
	}
	/**
	 * 设置：
	 */
	public void setSuffixcarcode(String suffixcarcode) {
		this.suffixcarcode = suffixcarcode;
	}
	/**
	 * 获取：
	 */
	public String getSuffixcarcode() {
		return suffixcarcode;
	}
	/**
	 * 设置：认证id
	 */
	public void setAuthid(Integer authid) {
		this.authid = authid;
	}
	/**
	 * 获取：认证id
	 */
	public Integer getAuthid() {
		return authid;
	}
}
