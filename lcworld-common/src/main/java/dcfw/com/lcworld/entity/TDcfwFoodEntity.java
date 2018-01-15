package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 订餐系统-食物
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
public class TDcfwFoodEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String name;
	//食材
	private String original;
	//价格
	private BigDecimal price;
	//图片
	private String img;
	//创建时间
	private Date createtime;
	//总数量
	private Integer totalcount;
	//剩余数量
	private Integer remain;
	//上传人id
	private Integer uploaduid;

	private String uploadname;

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
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：食材
	 */
	public void setOriginal(String original) {
		this.original = original;
	}
	/**
	 * 获取：食材
	 */
	public String getOriginal() {
		return original;
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
	 * 设置：图片
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：图片
	 */
	public String getImg() {
		return img;
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
	 * 设置：总数量
	 */
	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}
	/**
	 * 获取：总数量
	 */
	public Integer getTotalcount() {
		return totalcount;
	}
	/**
	 * 设置：剩余数量
	 */
	public void setRemain(Integer remain) {
		this.remain = remain;
	}
	/**
	 * 获取：剩余数量
	 */
	public Integer getRemain() {
		return remain;
	}
	/**
	 * 设置：上传人id
	 */
	public void setUploaduid(Integer uploaduid) {
		this.uploaduid = uploaduid;
	}
	/**
	 * 获取：上传人id
	 */
	public Integer getUploaduid() {
		return uploaduid;
	}

	public String getUploadname() {
		return uploadname;
	}

	public void setUploadname(String uploadname) {
		this.uploadname = uploadname;
	}
}
