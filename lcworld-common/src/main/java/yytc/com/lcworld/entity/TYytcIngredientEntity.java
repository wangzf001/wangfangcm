package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
public class TYytcIngredientEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer iId;
	//原料名称(包括用量)
	private String iNameNum;
	//菜单id
	private Integer mId;

	/**
	 * 设置：
	 */
	public void setIId(Integer iId) {
		this.iId = iId;
	}
	/**
	 * 获取：
	 */
	public Integer getIId() {
		return iId;
	}
	/**
	 * 设置：原料名称(包括用量)
	 */
	public void setINameNum(String iNameNum) {
		this.iNameNum = iNameNum;
	}
	/**
	 * 获取：原料名称(包括用量)
	 */
	public String getINameNum() {
		return iNameNum;
	}
	/**
	 * 设置：菜单id
	 */
	public void setMId(Integer mId) {
		this.mId = mId;
	}
	/**
	 * 获取：菜单id
	 */
	public Integer getMId() {
		return mId;
	}
}
