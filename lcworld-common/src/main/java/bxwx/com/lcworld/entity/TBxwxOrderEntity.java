package com.lcworld.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 报修维修订单
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-16 10:22:04
 */
public class TBxwxOrderEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//维修项目id
	private Integer menditem;
	//姓名
	private String username;
	//手机号
	private String mobile;
	//预约时间
	private String invitetime;
	//楼栋ID
	private Integer buildId;
	//服务地点
	private String serviceplace;
	//维修人员id
	private Integer menderid;
	//详情信息
	private String mendcontent;
	//维修图片信息(多逗号分割)
	private String mendimgs;
	//价格
	private BigDecimal price;
	//1:待接单，2： 已接单，3：已完成，4:待评价，5：已取消 
	private Integer orderstatus;
	//
	private Integer uid;
	//下单时间
	private Date createtime;
	//预约时间类型 1: 立即上门，2： 选择时间上门
	private Integer invitetimetype;
	//状态改变
	private Integer orderchange;
	//订单号
	private String ordercode;
	//1: 支付，0：没
	private Integer paystatus;
	//是否删除
	private Integer isdel = 0;
    //支付类型 (1:支付宝，2：微信，3：个人余额对私 ，4：集体余额对公，5：代金券对私，6：代金券对公)
    private Integer paytype;
    //取消原因
    private String cancelreason;
    private String mendername;
    //服务端删除状态
    private Integer realdel = 0;
    
    private Integer checkstatus;

	public Integer getCheckstatus() {
		return checkstatus;
	}
	public void setCheckstatus(Integer checkstatus) {
		this.checkstatus = checkstatus;
	}
	public Integer getRealdel() {
		return realdel;
	}
	public void setRealdel(Integer realdel) {
		this.realdel = realdel;
	}
	public String getMendername() {
        return mendername;
    }
    public void setMendername(String mendername) {
        this.mendername = mendername;
    }
    public String getCancelreason() {
        return cancelreason;
    }
    public void setCancelreason(String cancelreason) {
        this.cancelreason = cancelreason;
    }
    public Integer getPaytype() {
        return paytype;
    }
    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

	public Integer getBuildId() {
		return buildId;
	}

	public void setBuildId(Integer buildId) {
		this.buildId = buildId;
	}

	/**
	 * 设置：编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：维修项目id
	 */
	public void setMenditem(Integer menditem) {
		this.menditem = menditem;
	}
	/**
	 * 获取：维修项目id
	 */
	public Integer getMenditem() {
		return menditem;
	}
	/**
	 * 设置：姓名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：姓名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：预约时间
	 */
	public void setInvitetime(String invitetime) {
		this.invitetime = invitetime;
	}
	/**
	 * 获取：预约时间
	 */
	public String getInvitetime() {
		return invitetime;
	}
	/**
	 * 设置：服务地点
	 */
	public void setServiceplace(String serviceplace) {
		this.serviceplace = serviceplace;
	}
	/**
	 * 获取：服务地点
	 */
	public String getServiceplace() {
		return serviceplace;
	}
	/**
	 * 设置：维修人员id
	 */
	public void setMenderid(Integer menderid) {
		this.menderid = menderid;
	}
	/**
	 * 获取：维修人员id
	 */
	public Integer getMenderid() {
		return menderid;
	}
	/**
	 * 设置：详情信息
	 */
	public void setMendcontent(String mendcontent) {
		this.mendcontent = mendcontent;
	}
	/**
	 * 获取：详情信息
	 */
	public String getMendcontent() {
		return mendcontent;
	}
	/**
	 * 设置：维修图片信息(多逗号分割)
	 */
	public void setMendimgs(String mendimgs) {
		this.mendimgs = mendimgs;
	}
	/**
	 * 获取：维修图片信息(多逗号分割)
	 */
	public String getMendimgs() {
		return mendimgs;
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
	 * 设置：1:待接单，2： 已接单，3：已完成，4:待评价，5：已取消 
	 */
	public void setOrderstatus(Integer orderstatus) {
		this.orderstatus = orderstatus;
	}
	/**
	 * 获取：1:待接单，2： 已接单，3：已完成，4:待评价，5：已取消 
	 */
	public Integer getOrderstatus() {
		return orderstatus;
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
	 * 设置：下单时间
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	/**
	 * 获取：下单时间
	 */
	public Date getCreatetime() {
		return createtime;
	}
	/**
	 * 设置：预约时间类型 1: 立即上门，2： 选择时间上门
	 */
	public void setInvitetimetype(Integer invitetimetype) {
		this.invitetimetype = invitetimetype;
	}
	/**
	 * 获取：预约时间类型 1: 立即上门，2： 选择时间上门
	 */
	public Integer getInvitetimetype() {
		return invitetimetype;
	}
	/**
	 * 设置：状态改变
	 */
	public void setOrderchange(Integer orderchange) {
		this.orderchange = orderchange;
	}
	/**
	 * 获取：状态改变
	 */
	public Integer getOrderchange() {
		return orderchange;
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
	 * 设置：1: 支付，0：没
	 */
	public void setPaystatus(Integer paystatus) {
		this.paystatus = paystatus;
	}
	/**
	 * 获取：1: 支付，0：没
	 */
	public Integer getPaystatus() {
		return paystatus;
	}
	/**
	 * 设置：是否删除
	 */
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getIsdel() {
		return isdel;
	}
}
