package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-21 16:24:53
 */
public class FwOrderCancelReasonEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer autoId;
	//
	private String content;
	//服务类型id 1，报修维修，7会议室，8办公用品 ，18订水
	private Integer servicetypeid;

	/**
	 * 设置：
	 */
	public void setAutoId(Integer autoId) {
		this.autoId = autoId;
	}
	/**
	 * 获取：
	 */
	public Integer getAutoId() {
		return autoId;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：服务类型id 1，报修维修，7会议室，8办公用品 ，18订水
	 */
	public void setServicetypeid(Integer servicetypeid) {
		this.servicetypeid = servicetypeid;
	}
	/**
	 * 获取：服务类型id 1，报修维修，7会议室，8办公用品 ，18订水
	 */
	public Integer getServicetypeid() {
		return servicetypeid;
	}
}
