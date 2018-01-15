package com.lcworld.vo;

import java.math.BigDecimal;
import java.util.Date;

public class PurchaseCountVo {
    //
    private Integer id;
    //
    private Integer officeid;
    //创建时间
    private Date createtime;
    //可用余额
    private BigDecimal remain;
    //支付密码
    private String paypass;
    //采购账号类型
    private Integer typeid;
    //总金额
    private BigDecimal total;
    private String sourcepaypass;
    private String officename;
    
    private String departname;
    private Integer departid;
    private String typename;
    private Integer createuid;
    private PayOrderVo ordervo;
    
    public PayOrderVo getOrdervo() {
        return ordervo;
    }
    public void setOrdervo(PayOrderVo ordervo) {
        this.ordervo = ordervo;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getOfficeid() {
        return officeid;
    }
    public void setOfficeid(Integer officeid) {
        this.officeid = officeid;
    }
    public Date getCreatetime() {
        return createtime;
    }
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
    public BigDecimal getRemain() {
        return remain;
    }
    public void setRemain(BigDecimal remain) {
        this.remain = remain;
    }
    public String getPaypass() {
        return paypass;
    }
    public void setPaypass(String paypass) {
        this.paypass = paypass;
    }
    public Integer getTypeid() {
        return typeid;
    }
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }
    public BigDecimal getTotal() {
        return total;
    }
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public String getSourcepaypass() {
        return sourcepaypass;
    }
    public void setSourcepaypass(String sourcepaypass) {
        this.sourcepaypass = sourcepaypass;
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
    public String getTypename() {
        return typename;
    }
    public void setTypename(String typename) {
        this.typename = typename;
    }
    public Integer getCreateuid() {
        return createuid;
    }
    public void setCreateuid(Integer createuid) {
        this.createuid = createuid;
    }
    
    
}
