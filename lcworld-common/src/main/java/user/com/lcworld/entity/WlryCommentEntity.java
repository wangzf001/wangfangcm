package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 报修维修评论表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-09 11:05:54
 */
public class WlryCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//图片
	private String imgs;
	//评价内容
	private String content;
	//用户编号
	private Integer uid;
	//创建时间
	private Date createtime;
	//总分
	private Double score;
	//是否匿名 1；是 0:否
	private Integer anonymous;
	//服务态度
	private Double servicescore;
	//产品服务
	private Double productscore;

	/**
	 * 设置：编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：图片
	 */
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	/**
	 * 获取：图片
	 */
	public String getImgs() {
		return imgs;
	}
	/**
	 * 设置：评价内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：评价内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：用户编号
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户编号
	 */
	public Integer getUid() {
		return uid;
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
	 * 设置：总分
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	/**
	 * 获取：总分
	 */
	public Double getScore() {
		return score;
	}
	/**
	 * 设置：是否匿名 1；是 0:否
	 */
	public void setAnonymous(Integer anonymous) {
		this.anonymous = anonymous;
	}
	/**
	 * 获取：是否匿名 1；是 0:否
	 */
	public Integer getAnonymous() {
		return anonymous;
	}
	/**
	 * 设置：服务态度
	 */
	public void setServicescore(Double servicescore) {
		this.servicescore = servicescore;
	}
	/**
	 * 获取：服务态度
	 */
	public Double getServicescore() {
		return servicescore;
	}
	/**
	 * 设置：产品服务
	 */
	public void setProductscore(Double productscore) {
		this.productscore = productscore;
	}
	/**
	 * 获取：产品服务
	 */
	public Double getProductscore() {
		return productscore;
	}
}
