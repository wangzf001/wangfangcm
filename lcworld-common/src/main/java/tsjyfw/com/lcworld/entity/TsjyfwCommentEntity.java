package com.lcworld.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 图书借阅服务-评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-25 14:05:23
 */
public class TsjyfwCommentEntity implements Serializable {
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
	//图书编号
	private Integer bookid;
	//创建时间
	private Date createtime;
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
public Double getProductscore() {
	return productscore;
}
public void setProductscore(Double productscore) {
	this.productscore = productscore;
}
private List<String> imglist = new ArrayList<String>();
  

  public List<String> getImglist() {
    return imglist;
}
public void setImglist(List<String> imglist) {
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
	 * 设置：图书编号
	 */
	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}
	/**
	 * 获取：图书编号
	 */
	public Integer getBookid() {
		return bookid;
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
}
