package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 活动需求表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-22 18:59:09
 */
public class TdhdActivityDemandEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//活动ID
	private Integer activiyId;
	//年龄是否显示 0 否 1是
	private Integer age;
	//性别 0 否 1 是
	private Integer sex;
	//联系方式
	private Integer mobile;
	//姓名
	private Integer username;
	//民族
	private Integer nation;
	//单位
	private Integer depart;
	//处室
	private Integer office;
	//职位
	private Integer position;

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
	 * 设置：活动ID
	 */
	public void setActiviyId(Integer activiyId) {
		this.activiyId = activiyId;
	}
	/**
	 * 获取：活动ID
	 */
	public Integer getActiviyId() {
		return activiyId;
	}
	/**
	 * 设置：年龄是否显示 0 否 1是
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：年龄是否显示 0 否 1是
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：性别 0 否 1 是
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别 0 否 1 是
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：联系方式
	 */
	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：联系方式
	 */
	public Integer getMobile() {
		return mobile;
	}
	/**
	 * 设置：姓名
	 */
	public void setUsername(Integer username) {
		this.username = username;
	}
	/**
	 * 获取：姓名
	 */
	public Integer getUsername() {
		return username;
	}
	/**
	 * 设置：民族
	 */
	public void setNation(Integer nation) {
		this.nation = nation;
	}
	/**
	 * 获取：民族
	 */
	public Integer getNation() {
		return nation;
	}
	/**
	 * 设置：单位
	 */
	public void setDepart(Integer depart) {
		this.depart = depart;
	}
	/**
	 * 获取：单位
	 */
	public Integer getDepart() {
		return depart;
	}
	/**
	 * 设置：处室
	 */
	public void setOffice(Integer office) {
		this.office = office;
	}
	/**
	 * 获取：处室
	 */
	public Integer getOffice() {
		return office;
	}
	/**
	 * 设置：职位
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}
	/**
	 * 获取：职位
	 */
	public Integer getPosition() {
		return position;
	}
}
