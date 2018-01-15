package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 节能减排轮播图
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-15 16:41:31
 */
public class EnergyImgsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//
	private String imgUrl;
	//排序
	private Integer sort;
	//上传时间
	private String creatTime;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 获取：
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
	}
	/**
	 * 设置：上传时间
	 */
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	/**
	 * 获取：上传时间
	 */
	public String getCreatTime() {
		return creatTime;
	}
}
