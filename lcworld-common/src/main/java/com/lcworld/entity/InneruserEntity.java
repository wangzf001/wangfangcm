package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 内部人员表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-25 11:54:31
 */
public class InneruserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//姓名
	private String name;
	//电话
	private String mobile;
	//职务
	private String position;
	//部门
	private String depart;
	private Date deadline;
	

	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
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
	 * 设置：职务
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * 获取：职务
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * 设置：部门
	 */
	public void setDepart(String depart) {
		this.depart = depart;
	}
	/**
	 * 获取：部门
	 */
	public String getDepart() {
		return depart;
	}
}
