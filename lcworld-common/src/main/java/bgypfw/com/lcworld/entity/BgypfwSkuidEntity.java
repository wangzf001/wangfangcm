package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lcworld.dto.BgypSkuDto;



/**
 * 办公用品服务-规格
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:33
 */
public class BgypfwSkuidEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//规格名称
	private String skuname;
	//首图
	private String mainimg;
	//价格
	private BigDecimal price;
	//规格一级分类
	private Integer firstcataid;
	//规格二级分类
	private Integer secondcataid;
	//库存
	private Integer store;
	//创建时间
	private Date createtime;
	//上下架状态（1：上架，2：下架）
	private Integer status;
	//商品id
	private Integer productid;
	//sku分类名
	private String cataName;
	//sku是否为主规格
	private Integer isMain = 0;
	//上下架审核不通过原因
	private String failedreason;
	//上下架审核不通过原因列表
	private String failedreasonids;
	//规格描述
	private String gg;
	
	List<BgypfwSkuCataInfoEntity> catainfolist =new ArrayList<BgypfwSkuCataInfoEntity>();;
	
	
	
	
	public List<BgypfwSkuCataInfoEntity> getCatainfolist() {
		return catainfolist;
	}
	public void setCatainfolist(List<BgypfwSkuCataInfoEntity> catainfolist) {
		this.catainfolist = catainfolist;
	}
	public String getGg() {
		return gg;
	}
	public void setGg(String gg) {
		this.gg = gg;
	}
	public String getFailedreason() {
		return failedreason;
	}
	public void setFailedreason(String failedreason) {
		this.failedreason = failedreason;
	}
	public String getFailedreasonids() {
		return failedreasonids;
	}
	public void setFailedreasonids(String failedreasonids) {
		this.failedreasonids = failedreasonids;
	}
	public Integer getIsMain() {
		return isMain;
	}
	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}
	public String getCataName() {
		return cataName;
	}
	public void setCataName(String cataName) {
		this.cataName = cataName;
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
	 * 设置：规格名称
	 */
	public void setSkuname(String skuname) {
		this.skuname = skuname;
	}
	/**
	 * 获取：规格名称
	 */
	public String getSkuname() {
		return skuname;
	}
	/**
	 * 设置：首图
	 */
	public void setMainimg(String mainimg) {
		this.mainimg = mainimg;
	}
	/**
	 * 获取：首图
	 */
	public String getMainimg() {
		return mainimg;
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
	 * 设置：规格一级分类
	 */
	public void setFirstcataid(Integer firstcataid) {
		this.firstcataid = firstcataid;
	}
	/**
	 * 获取：规格一级分类
	 */
	public Integer getFirstcataid() {
		return firstcataid;
	}
	/**
	 * 设置：规格二级分类
	 */
	public void setSecondcataid(Integer secondcataid) {
		this.secondcataid = secondcataid;
	}
	/**
	 * 获取：规格二级分类
	 */
	public Integer getSecondcataid() {
		return secondcataid;
	}
	/**
	 * 设置：库存
	 */
	public void setStore(Integer store) {
		this.store = store;
	}
	/**
	 * 获取：库存
	 */
	public Integer getStore() {
		return store;
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
	 * 设置：上下架状态（1：上架，2：下架）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：上下架状态（1：上架，2：下架）
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：商品id
	 */
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	/**
	 * 获取：商品id
	 */
	public Integer getProductid() {
		return productid;
	}
}
