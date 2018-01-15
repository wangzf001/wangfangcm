package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 办公用品服务-申请商品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public class BgypfwApplyproductEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//申请人姓名
	private String username;
	//电话
	private String mobile;
	//商品名称
	private String productname;
	//商品数量
	private Integer productcount;
	//备注
	private String remark;
	//创建时间
	private Date createtime;
	//申请状态(1:未处理，2：已处理）
	private Integer status;
	//用户id
	private Integer uid;
	//失败原因
	private String failedReason;
	
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getFailedReason() {
		return failedReason;
	}
	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
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
	 * 设置：申请人姓名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：申请人姓名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：电话
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：商品名称
	 */
	public void setProductname(String productname) {
		this.productname = productname;
	}
	/**
	 * 获取：商品名称
	 */
	public String getProductname() {
		return productname;
	}
	/**
	 * 设置：商品数量
	 */
	public void setProductcount(Integer productcount) {
		this.productcount = productcount;
	}
	/**
	 * 获取：商品数量
	 */
	public Integer getProductcount() {
		return productcount;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
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
	 * 设置：申请状态(1:未处理，2：已处理）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：申请状态(1:未处理，2：已处理）
	 */
	public Integer getStatus() {
		return status;
	}
}
