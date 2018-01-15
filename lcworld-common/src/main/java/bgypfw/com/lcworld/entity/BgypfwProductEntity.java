package com.lcworld.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 办公用品服务-商品
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
public class BgypfwProductEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//商品名称
	private String productname;
	//价格范围
	private String pricerange;
	//创建时间
	private Date createtime;
	//主规格编号
	private Integer mainskuid;
	//上下架状态(1:上架，2：下架)
	private Integer status;
	//上架时间
	private Date onsaleTime;
	//一级分类id
	private Integer categoryOneId;
	//二级分类id
	private Integer categoryTwoId;
	//一级分类名
	private String categoryOneName;
	//二级分类名
	private String categoryTwoName;
	//主规格图片
	private String productImg;
	//主规格价格
	private String price;
	//轮播图
	private String imgs;
	private Integer favorNum;
	//供货单位名
	private String supplierName;
	//供货单位信息(预留)
	private String supplierInfo;
	//商品编号
	private String productCode;
	private String seller;
	
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	//轮播图List
//	private List<String> imgList = new ArrayList<>();
	private List<BgypfwProductimgEntity> imgEntityList = new ArrayList<>();
	
	public List<BgypfwProductimgEntity> getImgEntityList() {
		return imgEntityList;
	}
	public void setImgEntityList(List<BgypfwProductimgEntity> imgEntityList) {
		this.imgEntityList = imgEntityList;
	}
	public String getCategoryOneName() {
		return categoryOneName;
	}
	public void setCategoryOneName(String categoryOneName) {
		this.categoryOneName = categoryOneName;
	}
	public String getCategoryTwoName() {
		return categoryTwoName;
	}
	public void setCategoryTwoName(String categoryTwoName) {
		this.categoryTwoName = categoryTwoName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierInfo() {
		return supplierInfo;
	}
	public void setSupplierInfo(String supplierInfo) {
		this.supplierInfo = supplierInfo;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Integer getFavorNum() {
		return favorNum;
	}
	public void setFavorNum(Integer favorNum) {
		this.favorNum = favorNum;
	}
//	public List<String> getImgList() {
//		return imgList;
//	}
//	public void setImgList(List<String> imgList) {
//		this.imgList = imgList;
//	}
	public String getImgs() {
		return imgs;
	}
	public void setImgs(String imgs) {
		this.imgs = imgs;
	}
	public Integer getCategoryOneId() {
		return categoryOneId;
	}
	public void setCategoryOneId(Integer categoryOneId) {
		this.categoryOneId = categoryOneId;
	}
	public Integer getCategoryTwoId() {
		return categoryTwoId;
	}
	public void setCategoryTwoId(Integer categoryTwoId) {
		this.categoryTwoId = categoryTwoId;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
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
	 * 设置：商品名称
	 */
	public void setProductname(String productname) {
		this.productname = productname;
	}
	/**
	 * 获取：商品名称
	 */
	public String getProductname() {
		return productname;
	}
	/**
	 * 设置：价格范围
	 */
	public void setPricerange(String pricerange) {
		this.pricerange = pricerange;
	}
	/**
	 * 获取：价格范围
	 */
	public String getPricerange() {
		return pricerange;
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
	 * 设置：主规格编号
	 */
	public void setMainskuid(Integer mainskuid) {
		this.mainskuid = mainskuid;
	}
	/**
	 * 获取：主规格编号
	 */
	public Integer getMainskuid() {
		return mainskuid;
	}
	/**
	 * 设置：上下架状态(1:上架，2：下架)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：上下架状态(1:上架，2：下架)
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：上架时间
	 */
	public void setOnsaleTime(Date onsaleTime) {
		this.onsaleTime = onsaleTime;
	}
	/**
	 * 获取：上架时间
	 */
	public Date getOnsaleTime() {
		return onsaleTime;
	}
}
