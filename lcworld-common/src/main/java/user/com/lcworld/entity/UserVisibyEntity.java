package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-16 15:52:15
 */
public class UserVisibyEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//姓名
	private String name;
	//身份证号
	private String idcard;
	//访问人员id
	private Integer visituid;
	//
	private Integer id;
	//visitlog id
	private Integer vlid;

	public Integer getVlid() {
		return vlid;
	}
	public void setVlid(Integer vlid) {
		this.vlid = vlid;
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
	 * 设置：身份证号
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	/**
	 * 获取：身份证号
	 */
	public String getIdcard() {
		return idcard;
	}
	/**
	 * 设置：访问人员id
	 */
	public void setVisituid(Integer visituid) {
		this.visituid = visituid;
	}
	/**
	 * 获取：访问人员id
	 */
	public Integer getVisituid() {
		return visituid;
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
}
