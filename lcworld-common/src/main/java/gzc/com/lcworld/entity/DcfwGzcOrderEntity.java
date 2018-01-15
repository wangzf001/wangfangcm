package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 订餐服务-工作餐订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 13:35:13
 */
/**
 * @author leojr
 *
 */
public class DcfwGzcOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer uid;
	//订单号
	private String ordercode;
	//总价
	private BigDecimal totalprice;
	//1: 已预约，3：已完成，4：已评价，5：已取消
	private Integer status;
	//创建时间
	private Date createtime;
	//预留电话
	private String mobile;
	//真实姓名
	private String realname;
	//付款状态0未付款1已付款
	private Integer payStatus;
	//送达时间id
	private Integer sendfoodtime;
	//支付方式1在线支付
	private Integer payType;
	//取消订单ids
	private String cancelReasonids;
	//原因内容
	private String reasonContent;
	//预定数量
	private Integer count;
	//地址id
	private Integer addressId;
	//送达时间
	private String sendTime;
	//地址
	private String address;
	//单价
	private BigDecimal price;
	private Integer type;
	private String fixedmealtype;
	private Date fixedmealstartdate;
	private Date fixedmealenddate;
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getFixedmealtype() {
		return fixedmealtype;
	}
	public void setFixedmealtype(String fixedmealtype) {
		this.fixedmealtype = fixedmealtype;
	}
	public Date getFixedmealstartdate() {
		return fixedmealstartdate;
	}
	public void setFixedmealstartdate(Date fixedmealstartdate) {
		this.fixedmealstartdate = fixedmealstartdate;
	}
	public Date getFixedmealenddate() {
		return fixedmealenddate;
	}
	public void setFixedmealenddate(Date fixedmealenddate) {
		this.fixedmealenddate = fixedmealenddate;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	 * 设置：总价
	 */
	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}
	/**
	 * 获取：总价
	 */
	public BigDecimal getTotalprice() {
		return totalprice;
	}
	/**
	 * 设置：1: 已预约，3：已完成，4：已评价，5：已取消
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1: 已预约，3：已完成，4：已评价，5：已取消
	 */
	public Integer getStatus() {
		return status;
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
	 * 设置：预留电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：预留电话
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：真实姓名
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * 获取：真实姓名
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * 设置：付款状态0未付款1已付款
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * 获取：付款状态0未付款1已付款
	 */
	public Integer getPayStatus() {
		return payStatus;
	}
	/**
	 * 设置：送达时间
	 */
	public void setSendfoodtime(Integer sendfoodtime) {
		this.sendfoodtime = sendfoodtime;
	}
	/**
	 * 获取：送达时间
	 */
	public Integer getSendfoodtime() {
		return sendfoodtime;
	}
	/**
	 * 设置：支付方式1在线支付
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	/**
	 * 获取：支付方式1在线支付
	 */
	public Integer getPayType() {
		return payType;
	}
	/**
	 * 设置：取消订单ids
	 */
	public void setCancelReasonids(String cancelReasonids) {
		this.cancelReasonids = cancelReasonids;
	}
	/**
	 * 获取：取消订单ids
	 */
	public String getCancelReasonids() {
		return cancelReasonids;
	}
	/**
	 * 设置：原因内容
	 */
	public void setReasonContent(String reasonContent) {
		this.reasonContent = reasonContent;
	}
	/**
	 * 获取：原因内容
	 */
	public String getReasonContent() {
		return reasonContent;
	}
	/**
	 * 设置：预定数量
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：预定数量
	 */
	public Integer getCount() {
		return count;
	}
}
