package com.lcworld.dto;

import java.util.Date;
import java.util.List;

import com.lcworld.entity.TCarUsercarinfoEntity;

public class UserDTO {
    //
    private Integer id;
    //密码
    private String password;
    //创建时间
    private Date createtime;
    //手机号
    private String mobile;
    //用户昵称
    private Integer valid;
    //办公室座机
    private String tel;
    //房间号
    private String roomnum;
    //性别 1:男 0：女
    private Integer sex;
    //头像
    private String photo;
    //职位id
    private Integer positionid;
    //职位
    private String positionName;
    //身份证号
    private String idcard;
    //真实姓名
    private String realname;
    //认证状态
    private Integer status;
    private String buildnum;
    private String buildname;
    private Integer departid;
    private String departname;
    private String officename;
    private int hascumanager;
    private int haspumanager;
    //1:common ,2: pub
    private int usertype;
    private String username;
    private Date deadline;


    public String getBuildname() {
        return buildname;
    }

    public void setBuildname(String buildname) {
        this.buildname = buildname;
    }

    public Date getDeadline() {
        return deadline;
    }
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public int getUsertype() {
        return usertype;
    }
    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
    public int getHascumanager() {
        return hascumanager;
    }
    public void setHascumanager(int hascumanager) {
        this.hascumanager = hascumanager;
    }
    public int getHaspumanager() {
        return haspumanager;
    }
    public void setHaspumanager(int haspumanager) {
        this.haspumanager = haspumanager;
    }
    public String getOfficename() {
        return officename;
    }
    public void setOfficename(String officename) {
        this.officename = officename;
    }
    public String getDepartname() {
        return departname;
    }
    public void setDepartname(String departname) {
        this.departname = departname;
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
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public Integer getValid() {
        return valid;
    }
    public void setValid(Integer valid) {
        this.valid = valid;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getRoomnum() {
        return roomnum;
    }
    public void setRoomnum(String roomnum) {
        this.roomnum = roomnum;
    }
    public Integer getSex() {
        return sex;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public Integer getPositionid() {
        return positionid;
    }
    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }
    public String getPositionName() {
        return positionName;
    }
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
  
    public String getIdcard() {
        return idcard;
    }
    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    

    
    
}
