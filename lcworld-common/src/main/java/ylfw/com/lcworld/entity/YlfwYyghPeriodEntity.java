package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 医疗服务预约挂号-时间段表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 14:34:51
 */
public class YlfwYyghPeriodEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//开始时间
	private String starttime;
	//结束时间
	private String endtime;
	//时间段类型 1: 上午，2： 下午
	private Integer periodtype;

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
	 * 设置：开始时间
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	/**
	 * 获取：开始时间
	 */
	public String getStarttime() {
		return starttime;
	}
	/**
	 * 设置：结束时间
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	/**
	 * 获取：结束时间
	 */
	public String getEndtime() {
		return endtime;
	}
	/**
	 * 设置：时间段类型 1: 上午，2： 下午
	 */
	public void setPeriodtype(Integer periodtype) {
		this.periodtype = periodtype;
	}
	/**
	 * 获取：时间段类型 1: 上午，2： 下午
	 */
	public Integer getPeriodtype() {
		return periodtype;
	}
}
