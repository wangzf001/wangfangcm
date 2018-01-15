package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 订餐服务-取餐时间
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
public class TDcfwGetfoodtimeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String starttime;
	//
	private String endtime;
	//1: 上午，2： 下午
	private Integer periodtype;
	private String timeStr;

	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
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
	/**
	 * 设置：
	 */
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	/**
	 * 获取：
	 */
	public String getStarttime() {
		return starttime;
	}
	/**
	 * 设置：
	 */
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	/**
	 * 获取：
	 */
	public String getEndtime() {
		return endtime;
	}
	/**
	 * 设置：1: 上午，2： 下午
	 */
	public void setPeriodtype(Integer periodtype) {
		this.periodtype = periodtype;
	}
	/**
	 * 获取：1: 上午，2： 下午
	 */
	public Integer getPeriodtype() {
		return periodtype;
	}
}
