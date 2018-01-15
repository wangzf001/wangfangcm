package com.lcworld.dto;

public class BxwxMenderDTO {
    private Integer id;
    //真实姓名
    private String realname;
    //部门编号
    private Integer departid;
    //状态 1: 有效，0： 无效
    private Integer valid;
    //电话
    private String mobile;
    //登录编号
    private Integer loginid;
    //分数
    private Double score;
    //接单量
    private Integer count;
    
    
    /**
     * 擅长维修项
     */
    private String skilledName;

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

    public Integer getDepartid() {
        return departid;
    }

    public void setDepartid(Integer departid) {
        this.departid = departid;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getLoginid() {
        return loginid;
    }

    public void setLoginid(Integer loginid) {
        this.loginid = loginid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSkilledName() {
        return skilledName;
    }

    public void setSkilledName(String skilledName) {
        this.skilledName = skilledName;
    }
    
    
}
