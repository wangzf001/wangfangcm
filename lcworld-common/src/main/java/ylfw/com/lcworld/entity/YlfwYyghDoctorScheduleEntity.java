package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 医疗服务预约挂号——医生计划
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:51
 */
public class YlfwYyghDoctorScheduleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//医生id
	private Integer doctorid;
	//创建时间
	private Date createtime;
	//时间段id
	private Integer consultperiodid;
	//预约日期
	private Date scheduledate;
	//预约状态 0:未约，1：已约
	private Integer status;

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
	 * 设置：医生id
	 */
	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}
	/**
	 * 获取：医生id
	 */
	public Integer getDoctorid() {
		return doctorid;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：时间段id
	 */
	public void setConsultperiodid(Integer consultperiodid) {
		this.consultperiodid = consultperiodid;
	}
	/**
	 * 获取：时间段id
	 */
	public Integer getConsultperiodid() {
		return consultperiodid;
	}
	/**
	 * 设置：预约日期
	 */
	public void setScheduledate(Date scheduledate) {
		this.scheduledate = scheduledate;
	}
	/**
	 * 获取：预约日期
	 */
	public Date getScheduledate() {
		return scheduledate;
	}
	/**
	 * 设置：预约状态 0:未约，1：已约
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：预约状态 0:未约，1：已约
	 */
	public Integer getStatus() {
		return status;
	}
}
