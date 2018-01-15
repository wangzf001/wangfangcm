package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 部门
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-19 13:54:13
 */
public class DepartEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//部门编号
	private Integer id;
	//部门名称
	private String name;
	//办公用品对公账户管理类型 1： 统一管理支付，2：各自支付 
	private String bgyprighttype;
	

	public String getBgyprighttype() {
        return bgyprighttype;
    }
    public void setBgyprighttype(String bgyprighttype) {
        this.bgyprighttype = bgyprighttype;
    }
    /**
	 * 设置：部门编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：部门编号
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：部门名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：部门名称
	 */
	public String getName() {
		return name;
	}
}
