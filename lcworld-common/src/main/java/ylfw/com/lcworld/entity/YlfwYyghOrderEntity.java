package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 医疗服务预约挂号订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-21 18:22:47
 */
public class YlfwYyghOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer uid;
	//
	private Integer doctorid;
	//预约电话
	private String invitemobile;
	//预约计划id
	private Integer scheduleid;
	//创建时间
	private Date createtime;
	//订单状态改变 1: 改变，2： 未改变
	private Integer changes;
	//订单状态 1:已预约 ，2： 服务中（用户到店，医生点击），3：已完成（包含待评价和已评价），4：待评价，5：已取消
	private Integer status;
	//订单号
	private String ordercode;
	//支付时间
	private Date paytime;
	//支付金额
	private BigDecimal money;
	//30分钟提醒
	private Integer remind;
	//支付状态 1:已支付，0：未支付
	private Integer paytype;
	//挂号费单号
	private String ghcode;
	//挂号费支付状态 1:已支付，0： 未支付
	private Integer ghpaystatus;
	//挂号费
	private BigDecimal ghprice;
	//0:不删，1：已删
	private Integer isdel;
	//支付状态1：已支付，2：未支付
	private Integer paystatus;

	 //取消原因
    private String cancelreason;
    

    public String getCancelreason() {
        return cancelreason;
    }
    public void setCancelreason(String cancelreason) {
        this.cancelreason = cancelreason;
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
	 * 设置：
	 */
	public void setDoctorid(Integer doctorid) {
		this.doctorid = doctorid;
	}
	/**
	 * 获取：
	 */
	public Integer getDoctorid() {
		return doctorid;
	}
	/**
	 * 设置：预约电话
	 */
	public void setInvitemobile(String invitemobile) {
		this.invitemobile = invitemobile;
	}
	/**
	 * 获取：预约电话
	 */
	public String getInvitemobile() {
		return invitemobile;
	}
	/**
	 * 设置：预约计划id
	 */
	public void setScheduleid(Integer scheduleid) {
		this.scheduleid = scheduleid;
	}
	/**
	 * 获取：预约计划id
	 */
	public Integer getScheduleid() {
		return scheduleid;
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
	 * 设置：订单状态改变 1: 改变，2： 未改变
	 */
	public void setChanges(Integer changes) {
		this.changes = changes;
	}
	/**
	 * 获取：订单状态改变 1: 改变，2： 未改变
	 */
	public Integer getChanges() {
		return changes;
	}
	/**
	 * 设置：订单状态 1:已预约 ，2： 服务中（用户到店，医生点击），3：已完成（包含待评价和已评价），4：待评价，5：已取消
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态 1:已预约 ，2： 服务中（用户到店，医生点击），3：已完成（包含待评价和已评价），4：待评价，5：已取消
	 */
	public Integer getStatus() {
		return status;
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
	 * 设置：支付金额
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	/**
	 * 获取：支付金额
	 */
	public BigDecimal getMoney() {
		return money;
	}
	/**
	 * 设置：30分钟提醒
	 */
	public void setRemind(Integer remind) {
		this.remind = remind;
	}
	/**
	 * 获取：30分钟提醒
	 */
	public Integer getRemind() {
		return remind;
	}
	/**
	 * 设置：支付状态 1:已支付，0：未支付
	 */
	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	/**
	 * 获取：支付状态 1:已支付，0：未支付
	 */
	public Integer getPaytype() {
		return paytype;
	}
	/**
	 * 设置：挂号费单号
	 */
	public void setGhcode(String ghcode) {
		this.ghcode = ghcode;
	}
	/**
	 * 获取：挂号费单号
	 */
	public String getGhcode() {
		return ghcode;
	}
	/**
	 * 设置：挂号费支付状态 1:已支付，0： 未支付
	 */
	public void setGhpaystatus(Integer ghpaystatus) {
		this.ghpaystatus = ghpaystatus;
	}
	/**
	 * 获取：挂号费支付状态 1:已支付，0： 未支付
	 */
	public Integer getGhpaystatus() {
		return ghpaystatus;
	}
	/**
	 * 设置：挂号费
	 */
	public void setGhprice(BigDecimal ghprice) {
		this.ghprice = ghprice;
	}
	/**
	 * 获取：挂号费
	 */
	public BigDecimal getGhprice() {
		return ghprice;
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
	 * 设置：支付状态1：已支付，2：未支付
	 */
	public void setPaystatus(Integer paystatus) {
		this.paystatus = paystatus;
	}
	/**
	 * 获取：支付状态1：已支付，2：未支付
	 */
	public Integer getPaystatus() {
		return paystatus;
	}
}
