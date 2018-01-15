package com.lcworld.dto;

public class TsjyfwOrderDTO {
  //订单号
    private String ordercode;
    private Integer orderid;
    private String title;
    //作者
    private String author;
    //出版社
    private String publisher;
    //封面
    private String img;
    
    private Integer orderstatus;
    //下单时间
    private String createtime;
    
    private String invitegetbooktime;
    
    private Integer changes;
    
    public Integer getChanges() {
        return changes;
    }
    public void setChanges(Integer changes) {
        this.changes = changes;
    }
    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public String getInvitegetbooktime() {
        return invitegetbooktime;
    }
    public void setInvitegetbooktime(String invitegetbooktime) {
        this.invitegetbooktime = invitegetbooktime;
    }
    public Integer getOrderstatus() {
        return orderstatus;
    }
    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }
    public String getOrdercode() {
        return ordercode;
    }
    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }
    public Integer getOrderid() {
        return orderid;
    }
    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    
    
    
}
