package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/** 
 * 采购账户
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-09-01 10:54:46
 */
public class PurchaseAccountEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//可用余额
	private BigDecimal remain;
	//创建时间
	private Date createtime;
	//支付密码
	private String paypass;
	
	private Integer typeid;
	private BigDecimal total;
	
	

	public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public Integer getTypeid() {
        return typeid;
    }
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
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
	 * 设置：可用余额
	 */
	public void setRemain(BigDecimal remain) {
		this.remain = remain;
	}
	/**
	 * 获取：可用余额
	 */
	public BigDecimal getRemain() {
		return remain;
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
	 * 设置：支付密码
	 */
	public void setPaypass(String paypass) {
		this.paypass = paypass;
	}
	/**
	 * 获取：支付密码
	 */
	public String getPaypass() {
		return paypass;
	}
}
