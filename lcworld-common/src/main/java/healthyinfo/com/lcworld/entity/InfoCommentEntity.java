package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 资讯评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 15:14:38
 */
public class InfoCommentEntity implements Serializable {
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
	private Integer infoid;
	//总分
	private Double score;
	//服务分数
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
	public Double getProductscore() {
		return productscore;
	}
	public void setProductscore(Double productscore) {
		this.productscore = productscore;
	}
	public Double getServicescore() {
        return servicescore;
    }
    public void setServicescore(Double servicescore) {
        this.servicescore = servicescore;
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
	public void setInfoid(Integer infoid) {
		this.infoid = infoid;
	}
	/**
	 * 获取：资讯id
	 */
	public Integer getInfoid() {
		return infoid;
	}
}
