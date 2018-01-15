package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 办公用品服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 16:46:34
 */
public class BgypfwOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer uid;
	//
	private String code;
	//创建时间
	private Date createtime;
	//总价
	private BigDecimal totalprice;
	//送货地址
	private AddressEntity address = new AddressEntity();
	//备注
	private String remark;
	//支付类型(1:支付宝，2：微信，3：个人余额对公，4：个人余额对私 ，5：集体余额对公)
	private Integer paytype;
	//期望送达时间
	private Date expertarrivaltme;
	//下单人姓名
	private String username;
	//预留电话
	private String mobile;
	//订单状态1:已下单，2：配送中，3：已完成，4：待评价，5：已取消
	private Integer status;
	//地址id
	private Integer addressid;
	//取消订单原因ids
	private String cancelReasonids;
	//取消订单原因
	private String reasonContent;
	//支付状态 1:已支付
	private Integer payStatus;
	//退款状态 1:已退款
	private Integer refundstatus;
	//总单号
	private String parentOrdercode;
	//配送员
//	private DeliverymanEntity deliveryMan = new DeliverymanEntity();
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
	public Integer getRefundstatus() {
		return refundstatus;
	}
	public void setRefundstatus(Integer refundstatus) {
		this.refundstatus = refundstatus;
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
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public String getCancelReasonids() {
		return cancelReasonids;
	}
	public void setCancelReasonids(String cancelReasonids) {
		this.cancelReasonids = cancelReasonids;
	}
	public String getReasonContent() {
		return reasonContent;
	}
	public void setReasonContent(String reasonContent) {
		this.reasonContent = reasonContent;
	}
	private List<BgypfwOrderdetailEntity> orderDetailList = new ArrayList<>();
	
	public List<BgypfwOrderdetailEntity> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(List<BgypfwOrderdetailEntity> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	public Integer getAddressid() {
		return addressid;
	}
	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}
	public void setAddress(AddressEntity address) {
		this.address = address;
	}
	public AddressEntity getAddress() {
		return address;
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
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：
	 */
	public String getCode() {
		return code;
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
	 * 设置：支付类型(1:支付宝，2：微信，3：个人余额对公，4：个人余额对私 ，5：集体余额对公)
	 */
	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}
	/**
	 * 获取：支付类型(1:支付宝，2：微信，3：个人余额对公，4：个人余额对私 ，5：集体余额对公)
	 */
	public Integer getPaytype() {
		return paytype;
	}
	
	public Date getExpertarrivaltme() {
		return expertarrivaltme;
	}
	public void setExpertarrivaltme(Date expertarrivaltme) {
		this.expertarrivaltme = expertarrivaltme;
	}
	/**
	 * 设置：下单人姓名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：下单人姓名
	 */
	public String getUsername() {
		return username;
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
	 * 设置：订单状态1:已下单，2：配送中，3：已完成，4：待评价，5：已取消
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态1:已下单，2：配送中，3：已完成，4：待评价，5：已取消
	 */
	public Integer getStatus() {
		return status;
	}
}
