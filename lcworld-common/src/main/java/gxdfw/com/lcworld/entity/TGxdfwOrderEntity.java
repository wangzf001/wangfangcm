package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 干洗店服务-订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-11 14:35:53
 */
public class TGxdfwOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//预定人uid
	private Integer uid;
	//电话
	private String mobile;
	//预计送达时间
	private Date expectarrivaltime;
	//订单号
	private String code;
	//创建时间
	private Date createtime;
	//订单状态 1:已下单，2：服务中，3：已完成，4：待评价，5：已取消
	private Integer status;
	//总价
	private BigDecimal totalprice;
	//支付状态0未支付1已支付
	private Integer payStatus;
	//支付类型
	private Integer payType;
	//订单详情ids
	private String orderDetailIds;
	//订单log状态
	private Integer logStatus;
	//订单原因ids
	private String cancelReasonids;
	//订单原因内容
	private String reasonContent;
	//用户名
	private String nickname;
	//头像
	private String photo;
	//头像
	private Integer isevaluated;
	//
	private Integer checkstatus;
	private Integer checkstatus1;
	
	
	//订单详情列表
	private List<TGxdfwOrderdetailEntity> detailList = new ArrayList<>();
	
	
	public Integer getCheckstatus1() {
        return checkstatus1;
    }
    public void setCheckstatus1(Integer checkstatus1) {
        this.checkstatus1 = checkstatus1;
    }
    public Integer getCheckstatus() {
        return checkstatus;
    }
    public void setCheckstatus(Integer checkstatus) {
        this.checkstatus = checkstatus;
    }
    public Integer getIsevaluated() {
		return isevaluated;
	}
	public void setIsevaluated(Integer isevaluated) {
		this.isevaluated = isevaluated;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
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
	public String getOrderDetailIds() {
		return orderDetailIds;
	}
	public void setOrderDetailIds(String orderDetailIds) {
		this.orderDetailIds = orderDetailIds;
	}
	public Integer getLogStatus() {
		return logStatus;
	}
	public void setLogStatus(Integer logStatus) {
		this.logStatus = logStatus;
	}
	public List<TGxdfwOrderdetailEntity> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<TGxdfwOrderdetailEntity> detailList) {
		this.detailList = detailList;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getExpectarrivaltime() {
		return expectarrivaltime;
	}
	public void setExpectarrivaltime(Date expectarrivaltime) {
		this.expectarrivaltime = expectarrivaltime;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：预定人uid
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：预定人uid
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：电话
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：订单号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：订单号
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
	 * 设置：订单状态 1:已下单，2：服务中，3：已完成，4：待评价，5：已取消
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态 1:已下单，2：服务中，3：已完成，4：待评价，5：已取消
	 */
	public Integer getStatus() {
		return status;
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
}
