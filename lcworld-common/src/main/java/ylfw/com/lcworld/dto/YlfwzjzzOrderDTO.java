package com.lcworld.dto;

public class YlfwzjzzOrderDTO {
    private ZjzzExpertDTO doctor = new ZjzzExpertDTO();
    //
    private Integer id;
    //
    private Integer doctorid;
    //预约计划id
    private Integer scheduleid;
    //创建时间
    private String createtime;
    //订单状态改变 1: 改变，2： 未改变
    private Integer changes;
    //订单状态 1:已预约 ，2： 服务中（用户到店，医生点击），3：已完成（包含待评价和已评价），4：待评价，5：已取消
    private Integer status;
    //订单号
    private String ordercode;
    //30分钟提醒
    private Integer remind;
    private String scheduledate;
    private String photo;
    private String realname;
    private String invitemobile;

    public String getInvitemobile() {
        return invitemobile;
    }

    public void setInvitemobile(String invitemobile) {
        this.invitemobile = invitemobile;
    }

    public ZjzzExpertDTO getDoctor() {
        return doctor;
    }
    public void setDoctor(ZjzzExpertDTO doctor) {
        this.doctor = doctor;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getDoctorid() {
        return doctorid;
    }
    public void setDoctorid(Integer doctorid) {
        this.doctorid = doctorid;
    }
    public Integer getScheduleid() {
        return scheduleid;
    }
    public void setScheduleid(Integer scheduleid) {
        this.scheduleid = scheduleid;
    }
    public String getCreatetime() {
        return createtime;
    }
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
    public Integer getChanges() {
        return changes;
    }
    public void setChanges(Integer changes) {
        this.changes = changes;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getOrdercode() {
        return ordercode;
    }
    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }
    public Integer getRemind() {
        return remind;
    }
    public void setRemind(Integer remind) {
        this.remind = remind;
    }
    public String getScheduledate() {
        return scheduledate;
    }
    public void setScheduledate(String scheduledate) {
        this.scheduledate = scheduledate;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) {
        this.realname = realname;
    }
    
}
