package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 意见反馈表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-18 15:41:48
 */
public class XtszOpinionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer oid;
	//提交时间
	private Date createtime;
	//用户id
	private Integer uid;
	//意见内容
	private String content;
	//手机号
	private String mobile;
	//意见反馈模块类型：维修模块，1；办公用品，8；订水服务，18；会议室，7；
	private Integer type;

	/**
	 * 设置：主键
	 */
	public void setOid(Integer oid) {
		this.oid = oid;
	}
	/**
	 * 获取：主键
	 */
	public Integer getOid() {
		return oid;
	}
	/**
	 * 设置：提交时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：提交时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：用户id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：意见内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：意见内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：意见反馈模块类型：维修模块，1；办公用品，8；订水服务，18；会议室，7；
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：意见反馈模块类型：维修模块，1；办公用品，8；订水服务，18；会议室，7；
	 */
	public Integer getType() {
		return type;
	}
}
