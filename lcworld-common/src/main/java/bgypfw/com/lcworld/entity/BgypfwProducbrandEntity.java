package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 办公用品服务-商品品牌分类表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-20 20:10:14
 */
public class BgypfwProducbrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//品牌主键
	private Integer id;
	//品牌名
	private String brandName;
	//品牌图片地址
	private String imgUrl;
	//属性分类ID
	private Integer cataId;

	/**
	 * 设置：品牌主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：品牌主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：品牌名
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * 获取：品牌名
	 */
	public String getBrandName() {
		return brandName;
	}
	/**
	 * 设置：品牌图片地址
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * 获取：品牌图片地址
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * 设置：属性分类ID
	 */
	public void setCataId(Integer cataId) {
		this.cataId = cataId;
	}
	/**
	 * 获取：属性分类ID
	 */
	public Integer getCataId() {
		return cataId;
	}
}
