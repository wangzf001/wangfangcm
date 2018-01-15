package com.lcworld.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017-11-20 12:02:47
 */
public class VisiuserLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private String stamp;
    //
    private Integer status;
    // 来访人员表id
    private Integer vuid;
    //被访人id
    private Integer uid;
    private String realname;
    private String phonenum;
    //楼号ID
    private Integer buildnum;
    private String roomnum;
    private Integer visitchecked;
    private Integer checked;
    private String vname;
    private String vmobile;
    private String vidnum;
    private Integer vpnum;
    private Date visittime;
    private String unit;
    private Integer notify;
    private String ordercode;
    //管理员审核不过原因
    private String adminreason;
    //被访人取消原因
    private String visitreason;
    private String buildname;

    public String getBuildname() {
        return buildname;
    }

    public void setBuildname(String buildname) {
        this.buildname = buildname;
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

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public Integer getNotify() {
        return notify;
    }

    public void setNotify(Integer notify) {
        this.notify = notify;
    }

    public Integer getVisitchecked() {
        return visitchecked;
    }

    public void setVisitchecked(Integer visitchecked) {
        this.visitchecked = visitchecked;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getVmobile() {
        return vmobile;
    }

    public void setVmobile(String vmobile) {
        this.vmobile = vmobile;
    }

    public String getVidnum() {
        return vidnum;
    }

    public void setVidnum(String vidnum) {
        this.vidnum = vidnum;
    }

    public Integer getVpnum() {
        return vpnum;
    }

    public void setVpnum(Integer vpnum) {
        this.vpnum = vpnum;
    }

    public Date getVisittime() {
        return visittime;
    }

    public void setVisittime(Date visittime) {
        this.visittime = visittime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public Integer getBuildnum() {
        return buildnum;
    }

    public void setBuildnum(Integer buildnum) {
        this.buildnum = buildnum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
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
    public void setStamp(String stamp) {
        this.stamp = stamp;
    }

    /**
     * 获取：
     */
    public String getStamp() {
        return stamp;
    }

    /**
     * 设置：
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置： 来访人员表id
     */
    public void setVuid(Integer vuid) {
        this.vuid = vuid;
    }

    /**
     * 获取： 来访人员表id
     */
    public Integer getVuid() {
        return vuid;
    }

    /**
     * 设置：被访人id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取：被访人id
     */
    public Integer getUid() {
        return uid;
    }
}
