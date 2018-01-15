package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 图书借阅服务-时间段
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
public class TsjyfwPeriodEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//时间段
	private String timeperiod;
	//类型id 1: 上午，2： 下午
	private String typeid;
	//排序字段
	private Integer sort;

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
	 * 设置：时间段
	 */
	public void setTimeperiod(String timeperiod) {
		this.timeperiod = timeperiod;
	}
	/**
	 * 获取：时间段
	 */
	public String getTimeperiod() {
		return timeperiod;
	}
	/**
	 * 设置：类型id 1: 上午，2： 下午
	 */
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	/**
	 * 获取：类型id 1: 上午，2： 下午
	 */
	public String getTypeid() {
		return typeid;
	}
	/**
	 * 设置：排序字段
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序字段
	 */
	public Integer getSort() {
		return sort;
	}
}
