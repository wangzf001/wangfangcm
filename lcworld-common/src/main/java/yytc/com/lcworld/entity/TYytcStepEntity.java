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
public class TYytcStepEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer sId;
	//菜单id
	private Integer mId;
	//步骤内容
	private String sContent;
	//步骤配图
	private String sImg;
	//步骤顺序
	private Integer sSort;

	/**
	 * 设置：
	 */
	public void setSId(Integer sId) {
		this.sId = sId;
	}
	/**
	 * 获取：
	 */
	public Integer getSId() {
		return sId;
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
	/**
	 * 设置：步骤内容
	 */
	public void setSContent(String sContent) {
		this.sContent = sContent;
	}
	/**
	 * 获取：步骤内容
	 */
	public String getSContent() {
		return sContent;
	}
	/**
	 * 设置：步骤配图
	 */
	public void setSImg(String sImg) {
		this.sImg = sImg;
	}
	/**
	 * 获取：步骤配图
	 */
	public String getSImg() {
		return sImg;
	}
	/**
	 * 设置：步骤顺序
	 */
	public void setSSort(Integer sSort) {
		this.sSort = sSort;
	}
	/**
	 * 获取：步骤顺序
	 */
	public Integer getSSort() {
		return sSort;
	}
}
