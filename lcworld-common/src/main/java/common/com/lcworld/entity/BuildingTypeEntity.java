package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 楼栋建筑分类表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-07 10:53:13
 */
public class BuildingTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//建筑分类ID
	private Integer typeId;
	//建筑类型名称
	private String typeName;

	/**
	 * 设置：建筑分类ID
	 */
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	/**
	 * 获取：建筑分类ID
	 */
	public Integer getTypeId() {
		return typeId;
	}
	/**
	 * 设置：建筑类型名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * 获取：建筑类型名称
	 */
	public String getTypeName() {
		return typeName;
	}
}
