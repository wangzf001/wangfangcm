package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 采购账号记录
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-31 14:40:06
 */
public class PurchaseAccounlogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer uid;
	//采购账号id
	private Integer accountid;
	//订单id
	private Integer orderid;
	//订单类型 待完善
	private Integer ordertype;
	//详情
	private String detail;
	//创建时间
	private Date createtime;
	//金额
	private BigDecimal money;
	//订单类型名称
	private String ordertypename;
	//使用类型 2: 收入，1： 支出
	private Integer usetype;
	private Integer level;
	private String ordercode;
	
	

	public String getOrdercode() {
        return ordercode;
    }
    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
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
	 * 设置：采购账号id
	 */
	public void setAccountid(Integer accountid) {
		this.accountid = accountid;
	}
	/**
	 * 获取：采购账号id
	 */
	public Integer getAccountid() {
		return accountid;
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
	 * 设置：金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：金额
	 */
	public BigDecimal getMoney() {
		return money;
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
