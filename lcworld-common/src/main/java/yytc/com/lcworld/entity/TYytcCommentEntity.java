package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;



/**
 * 营养套餐评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-10 10:19:33
 */
public class TYytcCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//评论内容
	private String content;
	//评论时间
	private Date createtime;
	//评论人id
	private Integer uid;
	//资讯id
	private Integer mId;
	private String username;
	private String photo;
	//菜单标题
	private String mTitle;
	private Double score;
	private Double servicescore;
	private Double productscore;
	private String type;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Double getServicescore() {
		return servicescore;
	}
	public void setServicescore(Double servicescore) {
		this.servicescore = servicescore;
	}
	public Double getProductscore() {
		return productscore;
	}
	public void setProductscore(Double productscore) {
		this.productscore = productscore;
	}
	public String getmTitle() {
		return mTitle;
	}
	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：评论内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：评论内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：评论时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：评论时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：评论人id
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：评论人id
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：资讯id
	 */
	public void setMId(Integer mId) {
		this.mId = mId;
	}
	/**
	 * 获取：资讯id
	 */
	public Integer getMId() {
		return mId;
	}
}
