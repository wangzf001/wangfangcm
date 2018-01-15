package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 干洗店服务-订单详情
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:51
 */
public class TGxdfwOrderdetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//订单id
	private Integer orderid;
	//衣服类型id
	private Integer clothestypeid;
	//数量
	private Integer count;
	//单价
	private BigDecimal price;
	//详情总价
	private BigDecimal totalprice;
	//创建时间
	private Date createtime;
	//衣服名字
	private String clothName;
	//备注
	private String remark;
	
	

	public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getClothName() {
		return clothName;
	}
	public void setClothName(String clothName) {
		this.clothName = clothName;
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
	 * 设置：订单id
	 */
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	/**
	 * 获取：订单id
	 */
	public Integer getOrderid() {
		return orderid;
	}
	/**
	 * 设置：衣服类型id
	 */
	public void setClothestypeid(Integer clothestypeid) {
		this.clothestypeid = clothestypeid;
	}
	/**
	 * 获取：衣服类型id
	 */
	public Integer getClothestypeid() {
		return clothestypeid;
	}
	/**
	 * 设置：数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：数量
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：单价
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：详情总价
	 */
	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}
	/**
	 * 获取：详情总价
	 */
	public BigDecimal getTotalprice() {
		return totalprice;
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
}
