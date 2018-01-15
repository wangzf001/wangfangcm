package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 订餐服务订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 16:38:42
 */
public class TDcfwOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "TDcfwOrderEntity [id=" + id + ", uid=" + uid + ", ordercode=" + ordercode + ", totalprice=" + totalprice
				+ ", status=" + status + ", createtime=" + createtime + ", mobile=" + mobile + ", realname=" + realname
				+ ", getfoodtime=" + getfoodtime + ", expireTime=" + expireTime + ", payStatus=" + payStatus
				+ ", payType=" + payType + ", orderDetailIds=" + orderDetailIds + ", detailList=" + detailList + "]";
	}
	//
	private Integer id;
	//
	private Integer uid;
	//
	private String ordercode;
	//
	private BigDecimal totalprice;
	//1: 已预约，3：已完成，4：待评价，5：已取消
	private Integer status;
	//创建时间
	private Date createtime;
	//预留电话
	private String mobile;
	//真实姓名
	private String realname;
	//取餐时间
	private Integer getfoodtime;
	//取餐时间str
	private String takeTime;
	//过期时间
	private Date expireTime;
	//支付状态 1:已支付
	private Integer payStatus;
	//退款状态 1:已退款
	private Integer refundstatus;
	//支付方式
	private Integer payType;
	//订单详情ids
	private String orderDetailIds;
	//订单原因ids
	private String cancelReasonids;
	//订单原因内容
	private String reasonContent;
	//总单号
	private String parentOrdercode;
	//总价格
	private BigDecimal sumtotal;
	//总数量
	private Integer sumcount;
	//总数量
	private Integer packfood=0;
	//没有打包订单数
	private Integer undonum;
	//订单详情
	private List<TDcfwOrderdetailEntity> detailList = new ArrayList<>();
	
	public Integer getUndonum() {
		return undonum;
	}
	public void setUndonum(Integer undonum) {
		this.undonum = undonum;
	}
	public Integer getPackfood() {
		return packfood;
	}
	public void setPackfood(Integer packfood) {
		this.packfood = packfood;
	}
	public BigDecimal getSumtotal() {
		return sumtotal;
	}
	public void setSumtotal(BigDecimal sumtotal) {
		this.sumtotal = sumtotal;
		this.packfood = 1;
	}
	public Integer getSumcount() {
		return sumcount;
	}
	public void setSumcount(Integer sumcount) {
		this.sumcount = sumcount;
	}
	public Integer getRefundstatus() {
		return refundstatus;
	}
	public void setRefundstatus(Integer refundstatus) {
		this.refundstatus = refundstatus;
	}
	public String getParentOrdercode() {
		return parentOrdercode;
	}
	public void setParentOrdercode(String parentOrdercode) {
		this.parentOrdercode = parentOrdercode;
	}
	public String getReasonContent() {
		return reasonContent;
	}
	public void setReasonContent(String reasonContent) {
		this.reasonContent = reasonContent;
	}
	public String getCancelReasonids() {
		return cancelReasonids;
	}
	public void setCancelReasonids(String cancelReasonids) {
		this.cancelReasonids = cancelReasonids;
	}
	public String getTakeTime() {
		return takeTime;
	}
	public void setTakeTime(String takeTime) {
		this.takeTime = takeTime;
	}
	public String getOrderDetailIds() {
		return orderDetailIds;
	}
	public void setOrderDetailIds(String orderDetailIds) {
		this.orderDetailIds = orderDetailIds;
	}
	public List<TDcfwOrderdetailEntity> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<TDcfwOrderdetailEntity> detailList) {
		this.detailList = detailList;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public Date getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
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
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	/**
	 * 获取：
	 */
	public String getOrdercode() {
		return ordercode;
	}
	/**
	 * 设置：
	 */
	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getTotalprice() {
		return totalprice;
	}
	/**
	 * 设置：1: 已预约，3：已完成，4：待评价，5：已取消
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1: 已预约，3：已完成，4：待评价，5：已取消
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
	
	public Integer getGetfoodtime() {
		return getfoodtime;
	}
	public void setGetfoodtime(Integer getfoodtime) {
		this.getfoodtime = getfoodtime;
	}
}
