package com.lcworld.dto;

import java.math.BigDecimal;
import java.util.Date;

public class LffwOrderDTO {
    private Integer orderid;
  //订单号
    private String ordercode;
    //
    private Integer uid;
    //理发师id
    private Integer barberid;
    //创建时间
    private String createtime;
    //订单状态 1: 已预约，2：服务中，3：已完成，4：待评价，5：已取消
    private Integer status;
    //订单状态是否改变 1；是，0：否
    private Integer changes;
    //价格
    private BigDecimal price;
    
    //支付时间
    private Date paytime;
    //支付类型
    private Integer paytype;
    //0:不删，1：已删
    private Integer isdel;
    //支付状态1：已支付，2：未支付
    private Integer paystatus;
    
    private String realname;
    
    private String photo;
    
    private String invitetime;
    
    private Double score;
    
    private String addr;
    
    private String mobile;
    private Integer checkstatus;
    
    

    public Integer getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Integer checkstatus) {
        this.checkstatus = checkstatus;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getBarberid() {
        return barberid;
    }

    public void setBarberid(Integer barberid) {
        this.barberid = barberid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getChanges() {
        return changes;
    }

    public void setChanges(Integer changes) {
        this.changes = changes;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Integer getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer paytype) {
        this.paytype = paytype;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(Integer paystatus) {
        this.paystatus = paystatus;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getInvitetime() {
        return invitetime;
    }

    public void setInvitetime(String invitetime) {
        this.invitetime = invitetime;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
    
    
    
    
    

}
