package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 节能减排资讯信息
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-16 15:59:18
 */
public class EnergyInformationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//资讯标题
	private String title;
	//摘要
	private String abstracts;
	//资讯内容
	private String content;
	//图片地址
	private String img;
	//收藏量
	private Integer favorNum;
	//0 不推荐 1 推荐
	private Boolean isRecommend;
	//资讯来源
	private String sourceName;
	//来源地址
	private String sourceUrl;
	//创建时间
	private String creatTime;

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
	 * 设置：资讯标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：资讯标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：摘要
	 */
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	/**
	 * 获取：摘要
	 */
	public String getAbstracts() {
		return abstracts;
	}
	/**
	 * 设置：资讯内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：资讯内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：图片地址
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：图片地址
	 */
	public String getImg() {
		return img;
	}
	/**
	 * 设置：收藏量
	 */
	public void setFavorNum(Integer favorNum) {
		this.favorNum = favorNum;
	}
	/**
	 * 获取：收藏量
	 */
	public Integer getFavorNum() {
		return favorNum;
	}
	/**
	 * 设置：0 不推荐 1 推荐
	 */
	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}
	/**
	 * 获取：0 不推荐 1 推荐
	 */
	public Boolean getIsRecommend() {
		return isRecommend;
	}
	/**
	 * 设置：资讯来源
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	/**
	 * 获取：资讯来源
	 */
	public String getSourceName() {
		return sourceName;
	}
	/**
	 * 设置：来源地址
	 */
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	/**
	 * 获取：来源地址
	 */
	public String getSourceUrl() {
		return sourceUrl;
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
}
