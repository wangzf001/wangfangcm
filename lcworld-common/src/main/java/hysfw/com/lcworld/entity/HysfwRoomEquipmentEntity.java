package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-30 18:43:48
 */
public class HysfwRoomEquipmentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//实体id
	private Integer eid;
	//房间id
	private Integer roomid;
	//数量
	private Integer count;
	//类型1设备2服务
	private Integer type;

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
	 * 设置：实体id
	 */
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	/**
	 * 获取：实体id
	 */
	public Integer getEid() {
		return eid;
	}
	/**
	 * 设置：房间id
	 */
	public void setRoomid(Integer roomid) {
		this.roomid = roomid;
	}
	/**
	 * 获取：房间id
	 */
	public Integer getRoomid() {
		return roomid;
	}
	/**
	 * 设置：数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：数量
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：类型1设备2服务
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型1设备2服务
	 */
	public Integer getType() {
		return type;
	}
}
