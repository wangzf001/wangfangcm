package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 来访人员订单表
 *
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-12-19 12:38:52
 */
public class TVisitUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//来访人姓名
	private String vname;
	//来访人手机号
	private String vmobile;
	//楼号ID
	private Integer buildnum;
	//受访人房间号
	private String roomnum;
	//来访事由（预留）
	private String reason;
	//预计到达时间（预留）
	private Date expertarrivaltime;
	//访问时段 1: 一次，2：一星期，3：半个月，4：长期
	private Integer vperiod;
	//随行人数
	private Integer vpnum;
	//受访人uid
	private Integer uid;
	//管理员审核0待审核1审核通过2审核不通过
	private Integer checked;
	//来访人员身份证号
	private String vidnum;
	//受访人办公电话
	private String phonenum;
	//预计离开时间（预留）
	private Date expertleavetime;
	//是否有效
	private Integer valid;
	//受访人员姓名
	private String realname;
	//单位
	private String unit;
	//来访时间
	private Date visittime;
	//创建时间
	private Date createtime;
	//订单状态  1:已预约，3：已评论，4:待评价，5：已取消
	private Integer status;
	//取消原因
	private String cancelreason;
	//
	private Integer isdel;
	//
	private String ordercode;
	//
	private Integer notify;
	//管理员审核0待审核1审核通过2审核不通过
	private Integer visitchecked;
	//被访人拒绝原因
	private String visitreason;
	//管理员拒绝原因
	private String adminreason;

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
	 * 设置：来访人姓名
	 */
	public void setVname(String vname) {
		this.vname = vname;
	}
	/**
	 * 获取：来访人姓名
	 */
	public String getVname() {
		return vname;
	}
	/**
	 * 设置：来访人手机号
	 */
	public void setVmobile(String vmobile) {
		this.vmobile = vmobile;
	}
	/**
	 * 获取：来访人手机号
	 */
	public String getVmobile() {
		return vmobile;
	}
	/**
	 * 设置：楼号ID
	 */
	public void setBuildnum(Integer buildnum) {
		this.buildnum = buildnum;
	}
	/**
	 * 获取：楼号ID
	 */
	public Integer getBuildnum() {
		return buildnum;
	}
	/**
	 * 设置：受访人房间号
	 */
	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}
	/**
	 * 获取：受访人房间号
	 */
	public String getRoomnum() {
		return roomnum;
	}
	/**
	 * 设置：来访事由（预留）
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
	/**
	 * 获取：来访事由（预留）
	 */
	public String getReason() {
		return reason;
	}
	/**
	 * 设置：预计到达时间（预留）
	 */
	public void setExpertarrivaltime(Date expertarrivaltime) {
		this.expertarrivaltime = expertarrivaltime;
	}
	/**
	 * 获取：预计到达时间（预留）
	 */
	public Date getExpertarrivaltime() {
		return expertarrivaltime;
	}
	/**
	 * 设置：访问时段 1: 一次，2：一星期，3：半个月，4：长期
	 */
	public void setVperiod(Integer vperiod) {
		this.vperiod = vperiod;
	}
	/**
	 * 获取：访问时段 1: 一次，2：一星期，3：半个月，4：长期
	 */
	public Integer getVperiod() {
		return vperiod;
	}
	/**
	 * 设置：随行人数
	 */
	public void setVpnum(Integer vpnum) {
		this.vpnum = vpnum;
	}
	/**
	 * 获取：随行人数
	 */
	public Integer getVpnum() {
		return vpnum;
	}
	/**
	 * 设置：受访人uid
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：受访人uid
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：管理员审核0待审核1审核通过2审核不通过
	 */
	public void setChecked(Integer checked) {
		this.checked = checked;
	}
	/**
	 * 获取：管理员审核0待审核1审核通过2审核不通过
	 */
	public Integer getChecked() {
		return checked;
	}
	/**
	 * 设置：来访人员身份证号
	 */
	public void setVidnum(String vidnum) {
		this.vidnum = vidnum;
	}
	/**
	 * 获取：来访人员身份证号
	 */
	public String getVidnum() {
		return vidnum;
	}
	/**
	 * 设置：受访人办公电话
	 */
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	/**
	 * 获取：受访人办公电话
	 */
	public String getPhonenum() {
		return phonenum;
	}
	/**
	 * 设置：预计离开时间（预留）
	 */
	public void setExpertleavetime(Date expertleavetime) {
		this.expertleavetime = expertleavetime;
	}
	/**
	 * 获取：预计离开时间（预留）
	 */
	public Date getExpertleavetime() {
		return expertleavetime;
	}
	/**
	 * 设置：是否有效
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	/**
	 * 获取：是否有效
	 */
	public Integer getValid() {
		return valid;
	}
	/**
	 * 设置：受访人员姓名
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}
	/**
	 * 获取：受访人员姓名
	 */
	public String getRealname() {
		return realname;
	}
	/**
	 * 设置：单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 获取：单位
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * 设置：来访时间
	 */
	public void setVisittime(Date visittime) {
		this.visittime = visittime;
	}
	/**
	 * 获取：来访时间
	 */
	public Date getVisittime() {
		return visittime;
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
	 * 设置：订单状态  1:已预约，3：已评论，4:待评价，5：已取消
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：订单状态  1:已预约，3：已评论，4:待评价，5：已取消
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：取消原因
	 */
	public void setCancelreason(String cancelreason) {
		this.cancelreason = cancelreason;
	}
	/**
	 * 获取：取消原因
	 */
	public String getCancelreason() {
		return cancelreason;
	}
	/**
	 * 设置：
	 */
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	/**
	 * 获取：
	 */
	public Integer getIsdel() {
		return isdel;
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
	public void setNotify(Integer notify) {
		this.notify = notify;
	}
	/**
	 * 获取：
	 */
	public Integer getNotify() {
		return notify;
	}
	/**
	 * 设置：管理员审核0待审核1审核通过2审核不通过
	 */
	public void setVisitchecked(Integer visitchecked) {
		this.visitchecked = visitchecked;
	}
	/**
	 * 获取：管理员审核0待审核1审核通过2审核不通过
	 */
	public Integer getVisitchecked() {
		return visitchecked;
	}
	/**
	 * 设置：被访人拒绝原因
	 */
	public void setVisitreason(String visitreason) {
		this.visitreason = visitreason;
	}
	/**
	 * 获取：被访人拒绝原因
	 */
	public String getVisitreason() {
		return visitreason;
	}
	/**
	 * 设置：管理员拒绝原因
	 */
	public void setAdminreason(String adminreason) {
		this.adminreason = adminreason;
	}
	/**
	 * 获取：管理员拒绝原因
	 */
	public String getAdminreason() {
		return adminreason;
	}
}
