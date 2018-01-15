package com.lcworld.entity;

import java.io.Serializable;



/**
 * 楼栋表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-06 19:19:30
 */
public class BuildingEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//楼栋主键
	private Integer id;
	//楼栋名称
	private String buildName;
	//建筑类型ID
	private Integer buildType;
	
	private String typeName;
	/**
	 * 设置：楼栋主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：楼栋主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：楼栋名称
	 */
	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}
	/**
	 * 获取：楼栋名称
	 */
	public String getBuildName() {
		return buildName;
	}
	/**
	 * 设置：建筑类型ID
	 */
	public void setBuildType(Integer buildType) {
		this.buildType = buildType;
	}
	/**
	 * 获取：建筑类型ID
	 */
	public Integer getBuildType() {
		return buildType;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
