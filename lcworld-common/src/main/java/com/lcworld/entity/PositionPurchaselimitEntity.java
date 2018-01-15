package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 职位额度表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-25 14:35:19
 */
public class PositionPurchaselimitEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//职位id
	private Integer positionid;
	//服务类型
	private String servicecode;
	//对公额度
	private BigDecimal purchasecount;
	//职位
	private String position;
	//服务
	private String service;
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：职位id
	 */
	public void setPositionid(Integer positionid) {
		this.positionid = positionid;
	}
	/**
	 * 获取：职位id
	 */
	public Integer getPositionid() {
		return positionid;
	}
	/**
	 * 设置：服务类型
	 */
	public void setServicecode(String servicecode) {
		this.servicecode = servicecode;
	}
	/**
	 * 获取：服务类型
	 */
	public String getServicecode() {
		return servicecode;
	}
	/**
	 * 设置：对公额度
	 */
	public void setPurchasecount(BigDecimal purchasecount) {
		this.purchasecount = purchasecount;
	}
	/**
	 * 获取：对公额度
	 */
	public BigDecimal getPurchasecount() {
		return purchasecount;
	}
}
