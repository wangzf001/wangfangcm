package com.lcworld.entity;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import cn.jiguang.common.utils.StringUtils;



/**
 * 理发服务评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:37:14
 */
public class LffwCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//图片
	private String imgs;
	private List<String> imglist = new ArrayList<String>();
	//评价内容
	private String content;
	//订单编号
	private Integer orderid;
	//用户编号
	private Integer uid;
	//理发师编号
	private Integer barberid;
	//创建时间
	private Date createtime;
	//总分
	private Double score;
	//是否匿名(1:是，0：不是)
	private Integer anonymous;
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
	public Double getProductscore() {
		return productscore;
	}
	public void setProductscore(Double productscore) {
		this.productscore = productscore;
	}
	public List<String> getImglist() {
        return imglist;
    }
    public void setImglist(String imgs) {
    	List<String> imglist = new ArrayList<String>();
    	if(StringUtils.isNotEmpty(imgs)){
    		String[] imgArr = imgs.split(",");
        	imglist = Arrays.asList(imgArr);
    	}
        this.imglist = imglist;
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
	 * 设置：理发师编号
	 */
	public void setBarberid(Integer barberid) {
		this.barberid = barberid;
	}
	/**
	 * 获取：理发师编号
	 */
	public Integer getBarberid() {
		return barberid;
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
	 * 设置：是否匿名(1:是，0：不是)
	 */
	public void setAnonymous(Integer anonymous) {
		this.anonymous = anonymous;
	}
	/**
	 * 获取：是否匿名(1:是，0：不是)
	 */
	public Integer getAnonymous() {
		return anonymous;
	}
}