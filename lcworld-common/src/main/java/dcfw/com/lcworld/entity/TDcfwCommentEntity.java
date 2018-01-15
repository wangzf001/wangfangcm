package com.lcworld.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 订餐服务评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
public class TDcfwCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	//上传人编号
	private Integer supportid;
	//创建时间
	private Date createtime;
	//总分
	private Double score;
	//是否匿名(1:是，0：不是)
	private Integer anonymous;
	//用户名
	private String username;
	//用户头像
	private String photo;
	//服务分数
    private Double servicescore;
    private Double productscore;
    private String type ;
    private String mobile;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	//图片列表
	private List<String> imgsArr = new ArrayList<>();
	
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
	public List<String> getImgsArr() {
		return imgsArr;
	}
	public void setImgsArr(List<String> imgsArr) {
		this.imgsArr = imgsArr;
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
	 * 设置：上传人编号
	 */
	public void setSupportid(Integer supportid) {
		this.supportid = supportid;
	}
	/**
	 * 获取：上传人编号
	 */
	public Integer getSupportid() {
		return supportid;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
}