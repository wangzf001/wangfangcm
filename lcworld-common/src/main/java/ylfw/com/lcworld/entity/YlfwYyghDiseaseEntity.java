package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 医疗服务预约挂号疾病表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:52
 */
public class YlfwYyghDiseaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String name;
	//疾病编码
	private String diseasecode;

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
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：疾病编码
	 */
	public void setDiseasecode(String diseasecode) {
		this.diseasecode = diseasecode;
	}
	/**
	 * 获取：疾病编码
	 */
	public String getDiseasecode() {
		return diseasecode;
	}
}
