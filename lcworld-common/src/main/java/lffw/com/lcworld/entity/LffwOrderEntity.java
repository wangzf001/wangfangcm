package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 理发服务订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-23 16:57:59
 */
public class LffwOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//订单号
	private String ordercode;
	//
	private Integer uid;
	//理发师id
	private Integer barberid;
	//创建时间
	private Date createtime;
	//预留手机号
	private String mobile;
	//备注
	private String remark;
	//订单状态 1: 已预约，2：服务中，3：已完成，4：待评价，5：已取消
	private Integer status;
	//订单状态是否改变 1；是，0：否
	private Integer changes;
	//价格
	private BigDecimal price;
	//支付时间
	private Date paytime;
	//支付类型
	private Integer paytype;
	//0:不删，1：已删
	private Integer isdel;
	//支付状态1：已支付，0：未支付
	private Integer paystatus;
	//预约时间id
	private Integer scheduleid;
	
	private Date scheduledate;
	  //取消原因
    private String cancelreason;
    //1: 混合支付 0 ：普通支付
    private Integer mixpay;
    private Integer lockvouchercount;
    private Integer refundstatus;
    private Integer checkstatus;
    
    
    
    
    

    public Integer getCheckstatus() {
        return checkstatus;
    }
    public void setCheckstatus(Integer checkstatus) {
        this.checkstatus = checkstatus;
    }
    public Integer getLockvouchercount() {
        return lockvouchercount;
    }
    public void setLockvouchercount(Integer lockvouchercount) {
        this.lockvouchercount = lockvouchercount;
    }
    public Integer getRefundstatus() {
        return refundstatus;
    }
    public void setRefundstatus(Integer refundstatus) {
        this.refundstatus = refundstatus;
    }
    public Integer getMixpay() {
        return mixpay;
    }
    public void setMixpay(Integer mixpay) {
        this.mixpay = mixpay;
    }
    public String getCancelreason() {
        return cancelreason;
    }
    public void setCancelreason(String cancelreason) {
        this.cancelreason = cancelreason;
    }
	

	public Date getScheduledate() {
        return scheduledate;
    }
    public void setScheduledate(Date scheduledate) {
        this.scheduledate = scheduledate;
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
	 * 设置：订单号
	 */
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	/**
	 * 获取：订单号
	 */
	public String getOrdercode() {
		return ordercode;
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
	 * 设置：理发师id
	 */
	public void setBarberid(Integer barberid) {
		this.barberid = barberid;
	}
	/**
	 * 获取：理发师id
	 */
	public Integer getBarberid() {
		return barberid;
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
	 * 设置：预留手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：预留手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：备注
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：订单状态 1: 已预约，2：服务中，3：已完成，4：待评价，5：已取消
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态 1: 已预约，2：服务中，3：已完成，4：待评价，5：已取消
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：订单状态是否改变 1；是，0：否
	 */
	public void setChanges(Integer changes) {
		this.changes = changes;
	}
	/**
	 * 获取：订单状态是否改变 1；是，0：否
	 */
	public Integer getChanges() {
		return changes;
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
	 * 设置：支付时间
	 */
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	/**
	 * 获取：支付时间
	 */
	public Date getPaytime() {
		return paytime;
	}
	/**
	 * 设置：支付类型
	 */
	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	/**
	 * 获取：支付类型
	 */
	public Integer getPaytype() {
		return paytype;
	}
	/**
	 * 设置：0:不删，1：已删
	 */
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	/**
	 * 获取：0:不删，1：已删
	 */
	public Integer getIsdel() {
		return isdel;
	}
	/**
	 * 设置：支付状态1：已支付，0：未支付
	 */
	public void setPaystatus(Integer paystatus) {
		this.paystatus = paystatus;
	}
	/**
	 * 获取：支付状态1：已支付，0：未支付
	 */
	public Integer getPaystatus() {
		return paystatus;
	}
	/**
	 * 设置：预约时间id
	 */
	public void setScheduleid(Integer scheduleid) {
		this.scheduleid = scheduleid;
	}
	/**
	 * 获取：预约时间id
	 */
	public Integer getScheduleid() {
		return scheduleid;
	}
}
