package com.lcworld.dto;

import java.util.Date;

public class ZjzzExpertDTO {
    //
    private Integer id;
    //医生名称
    private String realname;
    //电话
    private String mobile;
    //医院
    private String hospitalName;
    //问诊量
    private Integer consultcount;
    //分数
    private Double score;
    //创建时间
    private Date createtime;
    //是否有效 1:有效，0-：无效
    private Integer valid;
    //简介
    private String brief;
    //图片
    private String photo;
    //预约人数
    private Integer invitetotalcount;
    //已预约人数
    private Integer remaincount;
    //工作时间
    private String worktime;
    //擅长
    private String skilledinfo;
    //
    private String positionname;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getHospitalName() {
        return hospitalName;
    }
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
    public Integer getConsultcount() {
        return consultcount;
    }
    public void setConsultcount(Integer consultcount) {
        this.consultcount = consultcount;
    }
    public Double getScore() {
        return score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
    public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public Integer getValid() {
        return valid;
    }
    public void setValid(Integer valid) {
        this.valid = valid;
    }
    public String getBrief() {
        return brief;
    }
    public void setBrief(String brief) {
        this.brief = brief;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public Integer getInvitetotalcount() {
        return invitetotalcount;
    }
    public void setInvitetotalcount(Integer invitetotalcount) {
        this.invitetotalcount = invitetotalcount;
    }
    public Integer getRemaincount() {
        return remaincount;
    }
    public void setRemaincount(Integer remaincount) {
        this.remaincount = remaincount;
    }
    public String getWorktime() {
        return worktime;
    }
    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }
    public String getSkilledinfo() {
        return skilledinfo;
    }
    public void setSkilledinfo(String skilledinfo) {
        this.skilledinfo = skilledinfo;
    }
    public String getPositionname() {
        return positionname;
    }
    public void setPositionname(String positionname) {
        this.positionname = positionname;
    }
    
}
