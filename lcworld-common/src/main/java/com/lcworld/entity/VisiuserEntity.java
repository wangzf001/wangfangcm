package com.lcworld.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * 来访人员
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-07 14:49:00
 */
public class VisiuserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//来访人姓名
	private String vname;
	//手机号
	private String vmobile;
	//受访人房间号
	private String roomnum;
	//受访人楼栋ID
	private Integer buildnum;
	private String buildName;
	//来访事由（预留）
	private String reason;
	//预计到达时间（预留）
	private Date expertarrivaltime;
	//访问时段 1: 一次，2：一星期，3：半个月，4：长期
	private Integer vperiod;
	//随行人数
	private String vpnum;
	//受访人uid
	private Integer uid;
	//审核0待审核1审核通过2审核不通过
	private Integer checked;
	//来访人员身份证号
	private String vidnum;
	//受访人办公电话
	private String phonenum;
	//预计离开时间（预留）
	private Date expertleavetime;
	//是否有效
	private Integer valid;
	//是否有效
	private String realname;
	private String ordercode;
	private Integer status;
	private String departname;
	private String officename;
	private Integer isdel;
	private Date createtime;
	private String token;
		//姓名
		private String username;
		//手机号
		private String mobile;
		//访问时段 1: 一次，2：一星期，3：半个月，4：长期
		private Integer visitperiod;
		//车辆类型
		private String cartype;
		//车牌号前缀
		private String carcode;
		//车牌号前缀
		private String unit;
		private String cancelreason;
		//通只被访者 1： 是，0：否
		private Integer notify;
		//操作类型 1： add,2:edit
		private Integer operatetype;
		private String stamp;
		private String visittimeStr;
		private Integer visitchecked;
		private Integer vlid;
		//1: 待提交订单，2： 来访页面
		private Integer from;
		//管理员审核不过原因
		private String adminreason;
		//被访人取消原因
		private String visitreason;
		
	private List<BuildingEntity> buildingEntityList;

	public List<BuildingEntity> getBuildingEntityList() {
		return buildingEntityList;
	}

	public void setBuildingEntityList(List<BuildingEntity> buildingEntityList) {
		this.buildingEntityList = buildingEntityList;
	}

	public String getAdminreason() {
			return adminreason;
		}
		public void setAdminreason(String adminreason) {
			this.adminreason = adminreason;
		}
		public String getVisitreason() {
			return visitreason;
		}
		public void setVisitreason(String visitreason) {
			this.visitreason = visitreason;
		}
	public Integer getFrom() {
			return from;
		}
		public void setFrom(Integer from) {
			this.from = from;
		}
	public Integer getVlid() {
			return vlid;
		}
		public void setVlid(Integer vlid) {
			this.vlid = vlid;
		}
	public Integer getVisitchecked() {
			return visitchecked;
		}
		public void setVisitchecked(Integer visitchecked) {
			this.visitchecked = visitchecked;
		}
	public String getVisittimeStr() {
			return visittimeStr;
		}
		public void setVisittimeStr(String visittimeStr) {
			this.visittimeStr = visittimeStr;
		}
	public String getStamp() {
			return stamp;
		}
		public void setStamp(String stamp) {
			this.stamp = stamp;
		}
	public Integer getOperatetype() {
			return operatetype;
		}
		public void setOperatetype(Integer operatetype) {
			this.operatetype = operatetype;
		}
	public Integer getNotify() {
			return notify;
		}
		public void setNotify(Integer notify) {
			this.notify = notify;
		}
	public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public Integer getVisitperiod() {
			return visitperiod;
		}
		public void setVisitperiod(Integer visitperiod) {
			this.visitperiod = visitperiod;
		}
		public String getCartype() {
			return cartype;
		}
		public void setCartype(String cartype) {
			this.cartype = cartype;
		}
		public String getCarcode() {
			return carcode;
		}
		public void setCarcode(String carcode) {
			this.carcode = carcode;
		}
		public Integer getBuildnum() {
			return buildnum;
		}
		public void setBuildnum(Integer buildnum) {
			this.buildnum = buildnum;
		}
		public String getUnit() {
			return unit;
		}
		public void setUnit(String unit) {
			this.unit = unit;
		}
		public String getCancelreason() {
			return cancelreason;
		}
		public void setCancelreason(String cancelreason) {
			this.cancelreason = cancelreason;
		}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	//来访时间
	private Date visittime;
	
	private List<UserVisibyEntity> byuserlist = new ArrayList<>();
	

    public Integer getIsdel() {
		return isdel;
	}
	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public List<UserVisibyEntity> getByuserlist() {
        return byuserlist;
    }
    public void setByuserlist(List<UserVisibyEntity> byuserlist) {
        this.byuserlist = byuserlist;
    }
    public Date getVisittime() {
        return visittime;
    }
    public void setVisittime(Date visittime) {
        this.visittime = visittime;
    }
    public String getDepartname() {
        return departname;
    }
    public void setDepartname(String departname) {
        this.departname = departname;
    }
    public String getOfficename() {
        return officename;
    }
    public void setOfficename(String officename) {
        this.officename = officename;
    }
    public String getOrdercode() {
        return ordercode;
    }
    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
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
	 * 设置：手机号
	 */
	public void setVmobile(String vmobile) {
		this.vmobile = vmobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getVmobile() {
		return vmobile;
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
	
	public String getBuildName() {
		return buildName;
	}
	public void setBuildName(String buildName) {
		this.buildName = buildName;
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
	public void setVpnum(String vpnum) {
		this.vpnum = vpnum;
	}
	/**
	 * 获取：随行人数
	 */
	public String getVpnum() {
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
	 * 设置：审核0待审核1审核通过2审核不通过
	 */
	public void setChecked(Integer checked) {
		this.checked = checked;
	}
	/**
	 * 获取：审核0待审核1审核通过2审核不通过
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
}
