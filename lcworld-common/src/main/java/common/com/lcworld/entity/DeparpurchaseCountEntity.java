package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-10-13 10:33:57
 */
public class DeparpurchaseCountEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer departid;
	//创建时间
	private Date createtime;
	//可用余额
	private BigDecimal remain;
	//支付密码
	private String paypass;
	//采购账号类型
	private Integer typeid;
	//总金额
	private BigDecimal total;
	private String sourcepaypass;
	private String departname;
	private String purchasetype;
	private Integer createuid;
	private String servicecode;
	
	
	
	
	

	public String getServicecode() {
        return servicecode;
    }
    public void setServicecode(String servicecode) {
        this.servicecode = servicecode;
    }
    public Integer getCreateuid() {
        return createuid;
    }
    public void setCreateuid(Integer createuid) {
        this.createuid = createuid;
    }
    public String getPurchasetype() {
        return purchasetype;
    }
    public void setPurchasetype(String purchasetype) {
        this.purchasetype = purchasetype;
    }
    public String getDepartname() {
        return departname;
    }
    public void setDepartname(String departname) {
        this.departname = departname;
    }
    public String getSourcepaypass() {
        return sourcepaypass;
    }
    public void setSourcepaypass(String sourcepaypass) {
        this.sourcepaypass = sourcepaypass;
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
	public void setDepartid(Integer departid) {
		this.departid = departid;
	}
	/**
	 * 获取：
	 */
	public Integer getDepartid() {
		return departid;
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
	/**
	 * 设置：采购账号类型
	 */
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	/**
	 * 获取：采购账号类型
	 */
	public Integer getTypeid() {
		return typeid;
	}
	/**
	 * 设置：总金额
	 */
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	/**
	 * 获取：总金额
	 */
	public BigDecimal getTotal() {
		return total;
	}
}
