package com.lcworld.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List; 



/**
 * 办公用品服务-评论
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public class BgypfwCommentEntity extends CommentEntity implements Serializable {
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
	//商品编号
	private Integer productid;
	//创建时间
	private Date createtime;
	//总分
	private Double score;
	//评价类别 1：好评 2：中评，3：差评
	private Integer commenttype;
	//是否匿名(1:是，0：不是)
	private Integer anonymous;
	//用户名
	private String realname;
	//用户头像
	private String photo;
	//服务分数
    private Double servicescore;
    //商品名
    private String productname;
    private Double productscore;
    private String type;
    
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
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public Double getServicescore() {
        return servicescore;
    }
    public void setServicescore(Double servicescore) {
        this.servicescore = servicescore;
    }
	
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	private List<String> imgList = new ArrayList<String>();
	
	public List<String> getImgList() {
		return imgList;
	}
	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
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
	 * 设置：商品编号
	 */
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	/**
	 * 获取：商品编号
	 */
	public Integer getProductid() {
		return productid;
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
	 * 设置：评价类别 1：好评 2：中评，3：差评
	 */
	public void setCommenttype(Integer commenttype) {
		this.commenttype = commenttype;
	}
	/**
	 * 获取：评价类别 1：好评 2：中评，3：差评
	 */
	public Integer getCommenttype() {
		return commenttype;
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
