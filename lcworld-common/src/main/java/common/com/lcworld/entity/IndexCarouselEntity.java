package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-13 14:16:27
 */
public class IndexCarouselEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//轮播图地址
	private String imgs;
	//排序
	private Integer sort;
	//跳转地址(预留)
	private String url;
	//停留时间(预留单位为秒)
	private Integer time;

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
	 * 设置：轮播图地址
	 */
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	/**
	 * 获取：轮播图地址
	 */
	public String getImgs() {
		return imgs;
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
	 * 设置：跳转地址(预留)
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：跳转地址(预留)
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：停留时间(预留单位为秒)
	 */
	public void setTime(Integer time) {
		this.time = time;
	}
	/**
	 * 获取：停留时间(预留单位为秒)
	 */
	public Integer getTime() {
		return time;
	}
}
