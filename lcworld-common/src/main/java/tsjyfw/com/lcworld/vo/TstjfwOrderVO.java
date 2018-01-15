package com.lcworld.vo;

public class TstjfwOrderVO {
    private Integer uid;
    //预约取书时间
    private String invitegetbooktime;
    //借阅人姓名
    private String uname;
    //借阅人电话
    private String umobile;
    private String bookids;
    
    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }
    public String getInvitegetbooktime() {
        return invitegetbooktime;
    }
    public void setInvitegetbooktime(String invitegetbooktime) {
        this.invitegetbooktime = invitegetbooktime;
    }
    public String getUname() {
        return uname;
    }
    public void setUname(String uname) {
        this.uname = uname;
    }
    public String getUmobile() {
        return umobile;
    }
    public void setUmobile(String umobile) {
        this.umobile = umobile;
    }
    public String getBookids() {
        return bookids;
    }
    public void setBookids(String bookids) {
        this.bookids = bookids;
    }
    
}
