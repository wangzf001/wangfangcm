package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 医疗服务预约挂号医生擅长疾病
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:51
 */
public class YlfwYyghDoctorSkilledEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer doctorid;
	//
	private Integer diseaseid;

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
	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}
	/**
	 * 获取：
	 */
	public Integer getDoctorid() {
		return doctorid;
	}
	/**
	 * 设置：
	 */
	public void setDiseaseid(Integer diseaseid) {
		this.diseaseid = diseaseid;
	}
	/**
	 * 获取：
	 */
	public Integer getDiseaseid() {
		return diseaseid;
	}
}
