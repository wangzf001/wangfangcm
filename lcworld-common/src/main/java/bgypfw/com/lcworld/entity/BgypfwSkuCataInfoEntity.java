package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-27 16:25:55
 */
public class BgypfwSkuCataInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer skuid;
	//规格分类id
	private Integer cataid;
	private Integer productid;
	//规格内容
	private String content;
	private String cataname;
	
	
	public String getCataname() {
		return cataname;
	}
	public void setCataname(String cataname) {
		this.cataname = cataname;
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
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
	public void setSkuid(Integer skuid) {
		this.skuid = skuid;
	}
	/**
	 * 获取：
	 */
	public Integer getSkuid() {
		return skuid;
	}
	/**
	 * 设置：规格分类id
	 */
	public void setCataid(Integer cataid) {
		this.cataid = cataid;
	}
	/**
	 * 获取：规格分类id
	 */
	public Integer getCataid() {
		return cataid;
	}
	/**
	 * 设置：规格内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：规格内容
	 */
	public String getContent() {
		return content;
	}
}
