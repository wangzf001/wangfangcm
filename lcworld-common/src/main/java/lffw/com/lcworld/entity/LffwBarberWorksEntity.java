package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 理发服务-理发师作品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 16:58:45
 */
public class LffwBarberWorksEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//列表页图片
	private String smallimg;
	//详情页图片
	private String detailimg;
	//简介
	private String brief;
	//价格
	private BigDecimal price;
	//创建时间
	private Date createtime;
	//作品名称
	private String name;
	//理发师id
	private Integer barberid;
	//显示首页 1: 显示首页，0：不显示
	private Integer showonindex;
	//内容
	private String content;
	//访问地址
	private String url;

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
	 * 设置：列表页图片
	 */
	public void setSmallimg(String smallimg) {
		this.smallimg = smallimg;
	}
	/**
	 * 获取：列表页图片
	 */
	public String getSmallimg() {
		return smallimg;
	}
	/**
	 * 设置：详情页图片
	 */
	public void setDetailimg(String detailimg) {
		this.detailimg = detailimg;
	}
	/**
	 * 获取：详情页图片
	 */
	public String getDetailimg() {
		return detailimg;
	}
	/**
	 * 设置：简介
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}
	/**
	 * 获取：简介
	 */
	public String getBrief() {
		return brief;
	}
	/**
	 * 设置：价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：作品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：作品名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：理发师id
	 */
	public void setBarberid(Integer barberid) {
		this.barberid = barberid;
	}
	/**
	 * 获取：理发师id
	 */
	public Integer getBarberid() {
		return barberid;
	}
	/**
	 * 设置：显示首页 1: 显示首页，0：不显示
	 */
	public void setShowonindex(Integer showonindex) {
		this.showonindex = showonindex;
	}
	/**
	 * 获取：显示首页 1: 显示首页，0：不显示
	 */
	public Integer getShowonindex() {
		return showonindex;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：访问地址
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：访问地址
	 */
	public String getUrl() {
		return url;
	}
}
