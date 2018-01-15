package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 代金券记录
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-01 11:39:39
 */
public class LffwVoucherLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer uid;
	//代金券id
	private Integer voucherid;
	//订单id
	private Integer orderid;
	//订单类型 待完善
	private Integer ordertype;
	//详情
	private String detail;
	//创建时间
	private Date createtime;
	//使用数量
	private Integer count;
	//订单类型名称
	private String ordertypename;
	//使用类型 1: 收入，2： 支出
	private Integer usetype;

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
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：代金券id
	 */
	public void setVoucherid(Integer voucherid) {
		this.voucherid = voucherid;
	}
	/**
	 * 获取：代金券id
	 */
	public Integer getVoucherid() {
		return voucherid;
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
	 * 设置：订单类型 待完善
	 */
	public void setOrdertype(Integer ordertype) {
		this.ordertype = ordertype;
	}
	/**
	 * 获取：订单类型 待完善
	 */
	public Integer getOrdertype() {
		return ordertype;
	}
	/**
	 * 设置：详情
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * 获取：详情
	 */
	public String getDetail() {
		return detail;
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
	 * 设置：使用数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：使用数量
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：订单类型名称
	 */
	public void setOrdertypename(String ordertypename) {
		this.ordertypename = ordertypename;
	}
	/**
	 * 获取：订单类型名称
	 */
	public String getOrdertypename() {
		return ordertypename;
	}
	/**
	 * 设置：使用类型 1: 收入，2： 支出
	 */
	public void setUsetype(Integer usetype) {
		this.usetype = usetype;
	}
	/**
	 * 获取：使用类型 1: 收入，2： 支出
	 */
	public Integer getUsetype() {
		return usetype;
	}
}
