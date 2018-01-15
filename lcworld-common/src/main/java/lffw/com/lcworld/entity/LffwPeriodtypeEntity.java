package com.lcworld.entity;

import java.io.Serializable;



/**
 * 理发服务--时间段类型
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 19:30:54
 */
public class LffwPeriodtypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//开始时间
	private String starttime;
	//结束时间
	private String endtime;
	//
	private String name;

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
}
