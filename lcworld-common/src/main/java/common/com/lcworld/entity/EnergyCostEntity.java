package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 能耗表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-13 15:50:14
 */
public class EnergyCostEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private Integer eId;
	//楼栋ID
	private Integer buildId;
	//1、电 2、水
	private Integer energyType;
	//当天能耗
	private BigDecimal energyCost;
	//创建时间
	private String creatTime;
	//楼栋名称
	private String buildName;

	/**
	 * 设置：主键ID
	 */
	public void setEId(Integer eId) {
		this.eId = eId;
	}
	/**
	 * 获取：主键ID
	 */
	public Integer getEId() {
		return eId;
	}
	/**
	 * 设置：楼栋ID
	 */
	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}
	/**
	 * 获取：楼栋ID
	 */
	public Integer getBuildId() {
		return buildId;
	}
	/**
	 * 设置：1、电 2、水
	 */
	public void setEnergyType(Integer energyType) {
		this.energyType = energyType;
	}
	/**
	 * 获取：1、电 2、水
	 */
	public Integer getEnergyType() {
		return energyType;
	}
	/**
	 * 设置：当天能耗
	 */
	public void setEnergyCost(BigDecimal energyCost) {
		this.energyCost = energyCost;
	}
	/**
	 * 获取：当天能耗
	 */
	public BigDecimal getEnergyCost() {
		return energyCost;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	/**
	 * 获取：创建时间
	 */
	public String getCreatTime() {
		return creatTime;
	}
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	
}
