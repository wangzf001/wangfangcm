package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-28 11:43:37
 */
public class BgypfwProducattrcataEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//商品属性分类
	private Integer id;
	//
	private Integer productid;
	//属性分类id
	private Integer cataid;
	private String cataname;
	private String content;
	
	

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCataname() {
		return cataname;
	}
	public void setCataname(String cataname) {
		this.cataname = cataname;
	}
	/**
	 * 设置：商品属性分类
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：商品属性分类
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	/**
	 * 获取：
	 */
	public Integer getProductid() {
		return productid;
	}
	/**
	 * 设置：属性分类id
	 */
	public void setCataid(Integer cataid) {
		this.cataid = cataid;
	}
	/**
	 * 获取：属性分类id
	 */
	public Integer getCataid() {
		return cataid;
	}
}
