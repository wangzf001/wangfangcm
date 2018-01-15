package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 团队活动系统-活动配图
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-24 13:45:52
 */
public class TdhdActivityimgEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer aiId;
	//
	private Integer aId;
	//
	private String aiImgPath;

	/**
	 * 设置：
	 */
	public void setAiId(Integer aiId) {
		this.aiId = aiId;
	}
	/**
	 * 获取：
	 */
	public Integer getAiId() {
		return aiId;
	}
	/**
	 * 设置：
	 */
	public void setAId(Integer aId) {
		this.aId = aId;
	}
	/**
	 * 获取：
	 */
	public Integer getAId() {
		return aId;
	}
	/**
	 * 设置：
	 */
	public void setAiImgPath(String aiImgPath) {
		this.aiImgPath = aiImgPath;
	}
	/**
	 * 获取：
	 */
	public String getAiImgPath() {
		return aiImgPath;
	}
}
