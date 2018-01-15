package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-15 14:53:07
 */
public class HysfwAppointmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//订单id
	private Integer orderid;
	//日期
	private Date date;
	//时间间隔ids
	private String intervalids;
	//状态1已预约2已取消
	private Integer status;
	//会议室id
	private Integer roomid;
	//用户id
	private Integer uid;
	
	public Integer getRoomid() {
		return roomid;
	}
	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
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
	 * 设置：订单id
	 */
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	/**
	 * 获取：订单id
	 */
	public Integer getOrderid() {
		return orderid;
	}
	/**
	 * 设置：日期
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * 获取：日期
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * 设置：时间间隔ids
	 */
	public void setIntervalids(String intervalids) {
		this.intervalids = intervalids;
	}
	/**
	 * 获取：时间间隔ids
	 */
	public String getIntervalids() {
		return intervalids;
	}
	/**
	 * 设置：状态1已预约2已取消
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态1已预约2已取消
	 */
	public Integer getStatus() {
		return status;
	}
}
