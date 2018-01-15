package com.lcworld.entity;

import java.util.Date;
import java.util.List;

public class CommentEntity {
	//编号
	private Integer id;
	//图片
	private String imgs;
	//评价内容
	private String content;
	//订单编号
	private Integer orderid;
	//用户编号
	private Integer uid;
	//维修人员编号
	private Integer menderid;
	//创建时间
	private Date createtime;
	//总分
	private Double score;
	//是否匿名 1；是 0:否
	private Integer anonymous;
	//服务分数
	private Double servicescore;
	//服务分数
	private Double productscore;
	
	private String type;
	//评论对象数组
	private List<ReplyCommentEntity> replyList;

	//评论者姓名
	private String replyname;

	//评论者头像
	private String replyphoto;

	public String getReplyphoto() {
		return replyphoto;
	}
	public void setReplyphoto(String replyphoto) {
		this.replyphoto = replyphoto;
	}
	public String getReplyname() {
		return replyname;
	}
	public void setReplyname(String replyname) {
		this.replyname = replyname;
	}
	public List<ReplyCommentEntity> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<ReplyCommentEntity> replyList) {
		this.replyList = replyList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	 * 设置：订单编号
	 */
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	/**
	 * 获取：订单编号
	 */
	public Integer getOrderid() {
		return orderid;
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
	 * 设置：维修人员编号
	 */
	public void setMenderid(Integer menderid) {
		this.menderid = menderid;
	}
	/**
	 * 获取：维修人员编号
	 */
	public Integer getMenderid() {
		return menderid;
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

}
