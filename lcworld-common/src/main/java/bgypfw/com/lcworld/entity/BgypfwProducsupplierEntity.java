package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 办公用品服务-商品供应商信息表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-20 20:10:14
 */
public class BgypfwProducsupplierEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//供应商ID
	private Integer id;
	//供应商名称（单位）
	private String supplierName;
	//供应商详细地址
	private String supplierAddress;
	//供应商联系方式
	private String supplierTel;
	//创建时间
	private String createTime;

	/**
	 * 设置：供应商ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：供应商ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：供应商名称（单位）
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	/**
	 * 获取：供应商名称（单位）
	 */
	public String getSupplierName() {
		return supplierName;
	}
	/**
	 * 设置：供应商详细地址
	 */
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	/**
	 * 获取：供应商详细地址
	 */
	public String getSupplierAddress() {
		return supplierAddress;
	}
	/**
	 * 设置：供应商联系方式
	 */
	public void setSupplierTel(String supplierTel) {
		this.supplierTel = supplierTel;
	}
	/**
	 * 获取：供应商联系方式
	 */
	public String getSupplierTel() {
		return supplierTel;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public String getCreateTime() {
		return createTime;
	}
}
