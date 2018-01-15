package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 理发服务-理发师
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:15
 */
public class LffwBarberEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String photo;
	//姓名
	private String name;
	//职称
	private Integer positionid;
	//分数
	private Double score;
	//电话
	private String mobile;
	//简介
	private String brief;
	//是否有效 1:有效，0：无
	private Integer valid;
	//地址
	private String address;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
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
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * 获取：
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：职称
	 */
	public void setPositionid(Integer positionid) {
		this.positionid = positionid;
	}
	/**
	 * 获取：职称
	 */
	public Integer getPositionid() {
		return positionid;
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
	 * 设置：简介
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}
	/**
	 * 获取：简介
	 */
	public String getBrief() {
		return brief;
	}
	/**
	 * 设置：是否有效 1:有效，0：无
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	/**
	 * 获取：是否有效 1:有效，0：无
	 */
	public Integer getValid() {
		return valid;
	}
}
