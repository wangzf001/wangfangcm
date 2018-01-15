package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-30 16:17:34
 */
public class HysfwConferenceServiceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//服务名
	private String name;
	//服务编号
	private String code;

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
	 * 设置：服务名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：服务名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：服务编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：服务编号
	 */
	public String getCode() {
		return code;
	}
}
