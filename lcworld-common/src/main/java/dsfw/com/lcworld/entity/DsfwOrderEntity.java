package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 订水服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-22 11:20:20
 */
public class DsfwOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	private Integer sendtimeid;
	//
	private Integer addressid;
	//
	private String remark;
	//
	private Integer payStatus;
	//
	private Integer payType;
	//
	private String cancelReasonids;
	//
	private String reasonContent;
	//送达时间
	private String sendTime;
	private String parentOrdercode ;
	private AddressEntity address;
	private String dusername;
	private String dmobile;
	private String dphoto;
	//服务端删除状态
	private Integer realdel;
	//服务人员id
	private Integer menderid;
	
	
	
	public Integer getMenderid() {
		return menderid;
	}
	public void setMenderid(Integer menderid) {
		this.menderid = menderid;
	}
	public Integer getRealdel() {
		return realdel;
	}
	public void setRealdel(Integer realdel) {
		this.realdel = realdel;
	}
	public String getDusername() {
		return dusername;
	}
	public void setDusername(String dusername) {
		this.dusername = dusername;
	}
	public String getDmobile() {
		return dmobile;
	}
	public void setDmobile(String dmobile) {
		this.dmobile = dmobile;
	}
	public String getDphoto() {
		return dphoto;
	}
	public void setDphoto(String dphoto) {
		this.dphoto = dphoto;
	}
	public String getParentOrdercode() {
		return parentOrdercode;
	}
	public void setParentOrdercode(String parentOrdercode) {
		this.parentOrdercode = parentOrdercode;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	private List<DsfwOrderdetailEntity> detailList = new ArrayList<>();
	
	public List<DsfwOrderdetailEntity> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<DsfwOrderdetailEntity> detailList) {
		this.detailList = detailList;
	}
	public AddressEntity getAddress() {
		return address;
	}
	public void setAddress(AddressEntity address) {
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
	/**
	 * 设置：取餐时间
	 */
	public void setSendtimeid(Integer sendtimeid) {
		this.sendtimeid = sendtimeid;
	}
	/**
	 * 获取：取餐时间
	 */
	public Integer getSendtimeid() {
		return sendtimeid;
	}
	/**
	 * 设置：
	 */
	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}
	/**
	 * 获取：
	 */
	public Integer getAddressid() {
		return addressid;
	}
	/**
	 * 设置：
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：
	 */
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * 获取：
	 */
	public Integer getPayStatus() {
		return payStatus;
	}
	/**
	 * 设置：
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	/**
	 * 获取：
	 */
	public Integer getPayType() {
		return payType;
	}
	/**
	 * 设置：
	 */
	public void setCancelReasonids(String cancelReasonids) {
		this.cancelReasonids = cancelReasonids;
	}
	/**
	 * 获取：
	 */
	public String getCancelReasonids() {
		return cancelReasonids;
	}
	/**
	 * 设置：
	 */
	public void setReasonContent(String reasonContent) {
		this.reasonContent = reasonContent;
	}
	/**
	 * 获取：
	 */
	public String getReasonContent() {
		return reasonContent;
	}
}
