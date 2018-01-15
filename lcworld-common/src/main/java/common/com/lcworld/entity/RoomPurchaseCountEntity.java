package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-30 15:30:18
 */
public class RoomPurchaseCountEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer roomid;
	//
	private Integer purchaseid;

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
	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}
	/**
	 * 获取：
	 */
	public Integer getRoomid() {
		return roomid;
	}
	/**
	 * 设置：
	 */
	public void setPurchaseid(Integer purchaseid) {
		this.purchaseid = purchaseid;
	}
	/**
	 * 获取：
	 */
	public Integer getPurchaseid() {
		return purchaseid;
	}
}
