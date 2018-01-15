package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 车辆类型
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-28 17:22:36
 */
public class TCarCartypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//类型名称
	private String typename;

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
	 * 设置：类型名称
	 */
	public void setTypename(String typename) {
		this.typename = typename;
	}
	/**
	 * 获取：类型名称
	 */
	public String getTypename() {
		return typename;
	}
}
