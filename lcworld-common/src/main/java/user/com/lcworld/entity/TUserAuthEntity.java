package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 认证表
 * 
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-08-29 10:51:23
 */
public class TUserAuthEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//姓名
	private String name;
	//电话
	private String mobile;
	//身份证号
	private String idcard;
	//职位编号
	private Integer positionid;
	//用户编号
	private Integer uid;
	//认证状态 0：未提交审核，1: 待审核，2：通过，3：不通过 
	private Integer status;
	//房间号
	private String roomnum;
	//用户车辆id
	private Integer carinfoid;
	//办公室电话
	private String officetel;
	private Integer nation;
	private String reason;
	private String buildnum;
	private Integer departid;
	private Integer officeid;
	private Integer sex;
	private Date birthday;
	private Date startworktime;
	private Date ldtime;
	private String email;

	
	
	
	

	public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public Date getStartworktime() {
        return startworktime;
    }
    public void setStartworktime(Date startworktime) {
        this.startworktime = startworktime;
    }
    public Date getLdtime() {
        return ldtime;
    }
    public void setLdtime(Date ldtime) {
        this.ldtime = ldtime;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getOfficeid() {
        return officeid;
    }
    public void setOfficeid(Integer officeid) {
        this.officeid = officeid;
    }
    public Integer getDepartid() {
        return departid;
    }
    public void setDepartid(Integer departid) {
        this.departid = departid;
    }
    public String getBuildnum() {
        return buildnum;
    }
    public void setBuildnum(String buildnum) {
        this.buildnum = buildnum;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public Integer getNation() {
        return nation;
    }
    public void setNation(Integer nation) {
        this.nation = nation;
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
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
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
	 * 设置：身份证号
	 */
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	/**
	 * 获取：身份证号
	 */
	public String getIdcard() {
		return idcard;
	}
	/**
	 * 设置：职位编号
	 */
	public void setPositionid(Integer positionid) {
		this.positionid = positionid;
	}
	/**
	 * 获取：职位编号
	 */
	public Integer getPositionid() {
		return positionid;
	}
	/**
	 * 设置：用户编号
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户编号
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：认证状态 0：未提交审核，1: 待审核，2：通过，3：不通过 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：认证状态 0：未提交审核，1: 待审核，2：通过，3：不通过 
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：房间号
	 */
	public void setRoomnum(String roomnum) {
		this.roomnum = roomnum;
	}
	/**
	 * 获取：房间号
	 */
	public String getRoomnum() {
		return roomnum;
	}
	/**
	 * 设置：用户车辆id
	 */
	public void setCarinfoid(Integer carinfoid) {
		this.carinfoid = carinfoid;
	}
	/**
	 * 获取：用户车辆id
	 */
	public Integer getCarinfoid() {
		return carinfoid;
	}
	/**
	 * 设置：办公室电话
	 */
	public void setOfficetel(String officetel) {
		this.officetel = officetel;
	}
	/**
	 * 获取：办公室电话
	 */
	public String getOfficetel() {
		return officetel;
	}
}
